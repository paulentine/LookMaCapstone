/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.paulentine.android.capstone;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.paulentine.android.capstone.adapter.RatingAdapter;
import com.paulentine.android.capstone.model.Rating;
import com.paulentine.android.capstone.model.Recipe;
import com.paulentine.android.capstone.util.RecipeUtil;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Transaction;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class RecipeDetailActivity extends AppCompatActivity implements
        View.OnClickListener,
        EventListener<DocumentSnapshot>,
        RatingDialogFragment.RatingListener {

    private static final String TAG = "RecipeDetail";

    public static final String KEY_RECIPE_ID = "key_recipe_id";

    private ImageView mImageView;
    private TextView mNameView;
    private MaterialRatingBar mRatingIndicator;
    private TextView mNumRatingsView;
    private TextView mCityView;
    private TextView mCategoryView;
    private TextView mPriceView;
    private ViewGroup mEmptyView;
    private RecyclerView mRatingsRecycler;

    private RatingDialogFragment mRatingDialog;

    private FirebaseFirestore mFirestore;
    private DocumentReference mRecipeRef;
    private ListenerRegistration mRecipeRegistration;

    private RatingAdapter mRatingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        
        mImageView = findViewById(R.id.recipe_image);
        mNameView = findViewById(R.id.recipe_name);
        mRatingIndicator = findViewById(R.id.recipe_rating);
        mNumRatingsView = findViewById(R.id.recipe_num_ratings);
        mCityView = findViewById(R.id.recipe_city);
        mCategoryView = findViewById(R.id.recipe_category);
        mPriceView = findViewById(R.id.recipe_price);
        mEmptyView = findViewById(R.id.view_empty_ratings);
        mRatingsRecycler = findViewById(R.id.recycler_ratings);

        findViewById(R.id.recipe_button_back).setOnClickListener(this);
        findViewById(R.id.fab_show_rating_dialog).setOnClickListener(this);

        // Get recipe ID from extras
        String recipeId = getIntent().getExtras().getString(KEY_RECIPE_ID);
        if (recipeId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_RECIPE_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the recipe
        mRecipeRef = mFirestore.collection("recipes").document(recipeId);

        // Get ratings
        Query ratingsQuery = mRecipeRef
                .collection("ratings")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(50);

        // RecyclerView
        mRatingAdapter = new RatingAdapter(ratingsQuery) {
            @Override
            protected void onDataChanged() {
                if (getItemCount() == 0) {
                    mRatingsRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mRatingsRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }
        };

        mRatingsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRatingsRecycler.setAdapter(mRatingAdapter);

        mRatingDialog = new RatingDialogFragment();
    }

    @Override
    public void onStart() {
        super.onStart();

        mRatingAdapter.startListening();
        mRecipeRegistration = mRecipeRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        mRatingAdapter.stopListening();

        if (mRecipeRegistration != null) {
            mRecipeRegistration.remove();
            mRecipeRegistration = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recipe_button_back:
                onBackArrowClicked(v);
                break;
            case R.id.fab_show_rating_dialog:
                onAddRatingClicked(v);
                break;
        }
    }

    private Task<Void> addRating(final DocumentReference recipeRef, final Rating rating) {
        // TODO(developer): Implement
//        return Tasks.forException(new Exception("not yet implemented"));
        // Create reference for new rating, for use inside the transaction
        final DocumentReference ratingRef = recipeRef.collection("ratings")
                .document();

        // In a transaction, add the new rating and update the aggregate totals
        return mFirestore.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction)
                    throws FirebaseFirestoreException {

                Recipe recipe = transaction.get(recipeRef)
                        .toObject(Recipe.class);

                // Compute new number of ratings
                int newNumRatings = recipe.getNumRatings() + 1;

                // Compute new average rating
                double oldRatingTotal = recipe.getAvgRating() *
                        recipe.getNumRatings();
                double newAvgRating = (oldRatingTotal + rating.getRating()) /
                        newNumRatings;

                // Set new recipe info
                recipe.setNumRatings(newNumRatings);
                recipe.setAvgRating(newAvgRating);

                // Commit to Firestore
                transaction.set(recipeRef, recipe);
                transaction.set(ratingRef, rating);

                return null;
            }
        });
    }

    /**
     * Listener for the Recipe document ({@link #mRecipeRef}).
     */
    @Override
    public void onEvent(DocumentSnapshot snapshot, FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "recipe:onEvent", e);
            return;
        }

        onRecipeLoaded(snapshot.toObject(Recipe.class));
    }

    private void onRecipeLoaded(Recipe recipe) {
        mNameView.setText(recipe.getName());
        mRatingIndicator.setRating((float) recipe.getAvgRating());
        mNumRatingsView.setText(getString(R.string.fmt_num_ratings, recipe.getNumRatings()));
        mCityView.setText(recipe.getCity());
        mCategoryView.setText(recipe.getCategory());
        mPriceView.setText(RecipeUtil.getPriceString(recipe));

        // Background image
        Glide.with(mImageView.getContext())
                .load(recipe.getPhoto())
                .into(mImageView);
    }

    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

    public void onAddRatingClicked(View view) {
        mRatingDialog.show(getSupportFragmentManager(), RatingDialogFragment.TAG);
    }

    @Override
    public void onRating(Rating rating) {
        // In a transaction, add the new rating and update the aggregate totals
        addRating(mRecipeRef, rating)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Rating added");

                        // Hide keyboard and scroll to top
                        hideKeyboard();
                        mRatingsRecycler.smoothScrollToPosition(0);
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Add rating failed", e);

                        // Show failure message and hide keyboard
                        hideKeyboard();
                        Snackbar.make(findViewById(android.R.id.content), "Failed to add rating",
                                Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

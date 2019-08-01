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

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paulentine.android.capstone.model.ExtendedIngredient;
import com.paulentine.android.capstone.model.Recipe;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.paulentine.android.capstone.model.Step;

public class RecipeDetailActivity extends AppCompatActivity implements
        View.OnClickListener,
        EventListener<DocumentSnapshot> {

    private static final String TAG = "RecipeDetail";

    public static final String KEY_RECIPE_ID = "key_recipe_id";

//    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mReadyInMinutesView;
    private TextView mServingsView;
    private TextView mIngredientsView;
    private TextView mStepsView;

    private ViewGroup mEmptyView;
    private RecyclerView mRatingsRecycler;

    private FirebaseFirestore mFirestore;
    private DocumentReference mRecipeRef;
    private ListenerRegistration mRecipeRegistration;
    private Recipe recipeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

//        mImageView = findViewById(R.id.recipe_image);
        mTitleView = findViewById(R.id.recipe_item_title);
        mReadyInMinutesView = findViewById((R.id.recipe_item_ready_in_minutes));
        mServingsView = findViewById(R.id.recipe_item_servings);
        mIngredientsView = findViewById(R.id.recipe_item_ingredient);
        mStepsView = findViewById(R.id.recipe_item_step);

        FloatingActionButton buttonStepUp = findViewById(R.id.button_step_up);
        buttonStepUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PAULINE", "THIS IS FROM BUTTON CLICKED");
                recipeModel.nextStep();
            }
        });

        findViewById(R.id.recipe_button_back).setOnClickListener(this);

        // Get recipe ID from extras
        String recipeId = getIntent().getExtras().getString(KEY_RECIPE_ID);
        if (recipeId == null) {
            throw new IllegalArgumentException("Must pass extra " + KEY_RECIPE_ID);
        }

        // Initialize Firestore
        mFirestore = FirebaseFirestore.getInstance();

        // Get reference to the recipe
        mRecipeRef = mFirestore.collection("testImgs").document(recipeId);
    }

    @Override
    public void onStart() {
        super.onStart();

        mRecipeRegistration = mRecipeRef.addSnapshotListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();

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
//            case R.id.fab_show_rating_dialog:
//                onAddRatingClicked(v);
//                break;
        }
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
        mTitleView.setText(recipe.getTitle());
        mReadyInMinutesView.setText(Integer.toString(recipe.getReadyInMinutes()));
        mServingsView.setText(Integer.toString(recipe.getServings()));

        // Bind recipe from snapshot to recipe model
        this.recipeModel = recipe;
        recipeModel.resetCursor();

        // Make currRecipe globally available for voice fulfillment
        Recipe.currRecipe = recipe;

        String stepsData = "";
        for (Step step : recipe.getSteps()) {
            stepsData += "\n" + step.getInstruction();
        }
        mStepsView.setText(stepsData);

        String ingredientsData = "";
        for (ExtendedIngredient extendedIngredient : recipe.getExtendedIngredients()) {
            ingredientsData += ("\n" + extendedIngredient.getAmount() + " " + extendedIngredient.getUnit() + " of " + extendedIngredient.getName());
        }
        mIngredientsView.setText(ingredientsData);

//        // Background image
//        Glide.with(mImageView.getContext())
//                .load(recipe.getImage())
//                .into(mImageView);
    }

    public void onBackArrowClicked(View view) {
        onBackPressed();
    }
}

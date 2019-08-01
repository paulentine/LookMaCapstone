package com.paulentine.android.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.paulentine.android.capstone.model.ExtendedIngredient;
import com.paulentine.android.capstone.model.Recipe;
import com.paulentine.android.capstone.model.Step;

import java.util.ArrayList;
import java.util.List;

public class SearchDetailActivity extends AppCompatActivity implements
        View.OnClickListener,
        EventListener<DocumentSnapshot> {

    private static final String TAG = "RecipeDetail";

    public static final String KEY_RECIPE_ID = "key_recipe_id";

    private ImageView mImageView;
    private TextView mTitleView;
    private TextView mReadyInMinutesView;
    private TextView mServingsView;
    private TextView mIngredientsView;
    private TextView mStepsView;

    private FirebaseFirestore mFirestore;
    private DocumentReference mRecipeRef;
    private ListenerRegistration mRecipeRegistration;
    private Recipe recipeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mTitleView = findViewById(R.id.recipe_item_title);
        mReadyInMinutesView = findViewById((R.id.recipe_item_ready_in_minutes));
        mServingsView = findViewById(R.id.recipe_item_servings);
        mIngredientsView = findViewById(R.id.recipe_item_ingredient);
        mImageView = findViewById(R.id.recipe_image);
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

        final Button mSaveButton = findViewById(R.id.search_button_add);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipe();
            }
        });
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

        // Background image
        Glide.with(mImageView.getContext())
                .load(recipe.getImage())
                .into(mImageView);

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
    }

    public void onBackArrowClicked(View view) {
        onBackPressed();
    }

    private void saveRecipe() {
        String title = editTextTitle.getText().toString();
        int readyInMinutes = Integer.parseInt(editTextReadyInMinutes.getText().toString());
        int servings = Integer.parseInt(editTextServings.getText().toString());
        String image = editTextImage.getText().toString();
        List<ExtendedIngredient> extendedIngredients = new ArrayList<ExtendedIngredient>();
        List<Step> steps = new ArrayList<Step>();

        // Ingredients
        final LinearLayout ingredientsLayout = findViewById(R.id.ingredients_linear_layout);
        int ingredientsCount = ingredientsLayout.getChildCount();
        Log.d("ingredientsCount", Integer.toString(ingredientsCount));

        for (int ingNum = 0; ingNum < ingredientsCount; ingNum++) {
            LinearLayout ingredientLayout = (LinearLayout)ingredientsLayout.getChildAt(ingNum);
            Log.d("ingredientLayout", (String.valueOf(ingredientLayout)));

            EditText editIngredientUnit = (EditText) ingredientLayout.getChildAt(1);
            String ingredientUnit = editIngredientUnit.getText().toString();

            EditText editIngredientAmount = (EditText) ingredientLayout.getChildAt(0);
            Integer ingredientAmount = Integer.parseInt(editIngredientAmount.getText().toString());

            EditText editIngredientName = (EditText) ingredientLayout.getChildAt(2);
            String ingredientName = editIngredientName.getText().toString();

            extendedIngredients.add(new ExtendedIngredient(ingredientName, ingredientAmount, ingredientUnit));
        }

        // Steps
        final LinearLayout stepsLayout = findViewById(R.id.steps_linear_layout);
        for (int stepNum = 0; stepNum < stepsCount; stepNum++) {
            EditText stepField = (EditText)stepsLayout.getChildAt(stepNum);
            String stepInstruction = stepField.getText().toString();
            steps.add(new Step(stepNum, stepInstruction));
        }

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference recipesRef = FirebaseFirestore.getInstance()
                .collection("testImgs");

        recipesRef.add(new Recipe(title, readyInMinutes, servings, image, extendedIngredients, steps));

        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
        finish();
    }
}

package com.paulentine.android.capstone;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paulentine.android.capstone.model.ExtendedIngredient;
import com.paulentine.android.capstone.model.Recipe;
import com.paulentine.android.capstone.model.Step;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NewRecipeActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextReadyInMinutes;
    private EditText editTextServings;
    private int stepsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextReadyInMinutes = findViewById(R.id.edit_text_ready_in_minutes);
        editTextServings = findViewById(R.id.edit_text_servings);

        final LinearLayout stepsLayout = findViewById(R.id.steps_linear_layout);
        final Button mNewStepButton = findViewById(R.id.recipe_button_new_step);
        mNewStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(stepsLayout.getContext());
                editText.setHint("Pre-heat oven to 400F");
                stepsCount++;
                Log.d("stepsCount", Integer.toString(stepsCount));
//                editText.setText("TextView " + String.valueOf(stepsCount));
                stepsLayout.addView(editText);
            }
        });

        final LinearLayout ingredientsLayout = (LinearLayout) findViewById(R.id.ingredients_linear_layout);

        final Button mNewIngredientButton = findViewById(R.id.recipe_button_new_ingredient);
        mNewIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout ingredientLayout = new LinearLayout(ingredientsLayout.getContext());

                EditText editAmount = new EditText(ingredientLayout.getContext());
                editAmount.setHint("1");

                EditText editUnit = new EditText(ingredientLayout.getContext());
                editUnit.setHint("cup");

                EditText editName = new EditText(ingredientLayout.getContext());
                editName.setHint("water");

                ingredientLayout.addView(editAmount);
                ingredientLayout.addView(editUnit);
                ingredientLayout.addView(editName);
                ingredientsLayout.addView(ingredientLayout);
            }
        });

        final Button mSaveButton = findViewById(R.id.recipe_button_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipe();
            }
        });
    }

    private void saveRecipe() {
        String title = editTextTitle.getText().toString();
        int readyInMinutes = Integer.parseInt(editTextReadyInMinutes.getText().toString());
        int servings = Integer.parseInt(editTextServings.getText().toString());
        List<ExtendedIngredient> extendedIngredients = new ArrayList<ExtendedIngredient>();
        List<Step> steps = new ArrayList<Step>();

        final LinearLayout stepsLayout = findViewById(R.id.steps_linear_layout);
//        int count = stepsLayout.getChildCount();
        for (int stepNum = 0; stepNum < stepsCount; stepNum++) {
            EditText stepField = (EditText)stepsLayout.getChildAt(stepNum);
            String stepInstruction = stepField.getText().toString();
            steps.add(new Step(stepNum, stepInstruction));
        }

        final LinearLayout ingredientsLayout = findViewById(R.id.ingredients_linear_layout);

        int ingredientsCount = ingredientsLayout.getChildCount();
        Log.d("ingredientsCount", Integer.toString(ingredientsCount));

        for (int ingNum = 0; ingNum < ingredientsCount; ingNum++) {
            // Use findViewWithTag

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

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference recipesRef = FirebaseFirestore.getInstance()
                .collection("testRecipes");

        recipesRef.add(new Recipe(title, readyInMinutes, servings, extendedIngredients, steps));

        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
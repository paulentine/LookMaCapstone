package com.paulentine.android.capstone;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paulentine.android.capstone.model.Recipe;
import com.paulentine.android.capstone.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewRecipeActivity extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextTitle;
    private EditText editTextReadyInMinutes;
    private EditText editTextServings;
    private int stepsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        editTextId = findViewById(R.id.edit_text_id);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextReadyInMinutes = findViewById(R.id.edit_text_ready_in_minutes);
        editTextServings = findViewById(R.id.edit_text_servings);

        final LinearLayout stepsLayout = findViewById(R.id.steps_linear_layout);
        final Button mNewStepButton = findViewById(R.id.recipe_button_new_step);
        mNewStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(stepsLayout.getContext());
                stepsCount++;
                Log.d("stepsCount", Integer.toString(stepsCount));
//                editText.setText("TextView " + String.valueOf(stepsCount));
                stepsLayout.addView(editText);
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
        int id = Integer.parseInt(editTextId.getText().toString());
        String title = editTextTitle.getText().toString();
        int readyInMinutes = Integer.parseInt(editTextReadyInMinutes.getText().toString());
        int servings = Integer.parseInt(editTextServings.getText().toString());
        List<Step> steps = new ArrayList<Step>();

        final LinearLayout stepsLayout = findViewById(R.id.steps_linear_layout);
        int count = stepsLayout.getChildCount();
        for (int stepNum = 1; stepNum < stepsCount; stepNum++) {
            EditText stepField = (EditText)stepsLayout.getChildAt(stepNum);
            String stepInstruction = stepField.getText().toString();
            steps.add(new Step(stepNum, stepInstruction));
        }

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference recipesRef = FirebaseFirestore.getInstance()
                .collection("testRecipes");

        recipesRef.add(new Recipe(id, title, readyInMinutes, servings, steps));

        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
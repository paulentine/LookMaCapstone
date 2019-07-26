package com.paulentine.android.capstone;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText editTextSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24px);
//        setTitle("Add Recipe");

        editTextId = findViewById(R.id.edit_text_id);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextReadyInMinutes = findViewById(R.id.edit_text_ready_in_minutes);
        editTextServings = findViewById(R.id.edit_text_servings);
        editTextSteps = findViewById(R.id.edit_text_steps);

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

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference recipesRef = FirebaseFirestore.getInstance()
                .collection("testRecipes");

        // Fake list of steps
        List<Step> fakeSteps = new ArrayList<Step>();
        fakeSteps.add(new Step(1, "Turn on oven"));
        fakeSteps.add(new Step(2, "Turn off oven"));
        fakeSteps.add(new Step(3, "Drink water"));
        recipesRef.add(new Recipe(id, title, readyInMinutes, servings, fakeSteps));
//
//        // Got this from some tutorial, obv doesn't work cos data structure doesn't match
//        String stepInput = editTextSteps.getText().toString();
//        String[] stepArray = stepInput.split("\\s*,\\s*");
//        List<String> steps = Arrays.asList(stepArray);
//        recipesRef.add(new Recipe(id, title, readyInMinutes, servings, steps));

//        // TODO: Match data structure w/ form so we can send real steps
//        // For each step from our input, we want to instantiate a new Step, with number & instruction
//        recipesRef.add(new Recipe(id, title, readyInMinutes, servings, steps));

        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
        finish();
    }
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.new_recipe_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.save_recipe:
//                saveRecipe();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    public boolean onSaveButtonClicked(Button button) {
//        switch (button.getItemId()) {
//            case R.id.save_recipe:
//                saveRecipe();
//                return true;
//            default:
//                return super.onSaveButtonClicked(button);
//        }
//    }

package com.paulentine.android.capstone;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paulentine.android.capstone.model.Recipe;

public class NewRecipeActivity extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextTitle;
    private EditText editTextReadyInMinutes;
    private EditText editTextServings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24px);
        setTitle("Add Note");

        editTextId = findViewById(R.id.edit_text_id);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextReadyInMinutes = findViewById(R.id.edit_text_ready_in_minutes);
        editTextServings = findViewById(R.id.edit_text_servings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_recipe_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_recipe:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        int id = Integer.parseInt(editTextId.getText().toString());
        String title = editTextTitle.getText().toString();
        int readyInMinutes = Integer.parseInt(editTextReadyInMinutes.getText().toString());
        int servings = Integer.parseInt(editTextServings.getText().toString());

        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference recipeBookRef = FirebaseFirestore.getInstance()
                .collection("RecipeBook");
        recipeBookRef.add(new Recipe(id, title, readyInMinutes, servings));
        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
        finish();
    }
}
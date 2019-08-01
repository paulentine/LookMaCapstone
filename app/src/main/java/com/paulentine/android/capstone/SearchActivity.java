package com.paulentine.android.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.paulentine.android.capstone.adapter.RecipeAdapter;
import com.paulentine.android.capstone.adapter.SearchAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SearchActivity extends AppCompatActivity implements
        SearchAdapter.OnSearchSelectedListener {

    private Toolbar mToolbar;
    private RecyclerView mSearchRecycler;
    private ViewGroup mEmptyView;
    private Query mQuery;
    private Button mSearchButton;
    private SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSearchRecycler = findViewById(R.id.recycler_search);

        mSearchButton = findViewById(R.id.search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRecyclerView();
            }
        });

    }

    private void initRecyclerView() {
        if (mQuery == null) {
            Log.w("searchRecyclerView", "No JSON, not initializing RecyclerView");
        }

        mAdapter = new SearchAdapter(mQuery, this) {

            @Override
            protected void onDataChanged() {
                // Show/hide content if the query returns empty.
                if (getItemCount() == 0) {
                    mSearchRecycler.setVisibility(View.GONE);
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mSearchRecycler.setVisibility(View.VISIBLE);
                    mEmptyView.setVisibility(View.GONE);
                }
            }

            @Override
            protected void onError(FirebaseFirestoreException e) {
                // Show a snackbar on errors
                Snackbar.make(findViewById(android.R.id.content),
                        "Error: check logs for info.", Snackbar.LENGTH_LONG).show();
            }
        };

        mRecipesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecipesRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onSearchSelected(DocumentSnapshot recipe) {
        // Go to the details page for the selected recipe
        Intent intent = new Intent(this, SearchDetailActivity.class);
//        intent.putExtra(SearchDetailActivity.KEY_RECIPE_ID, recipe.getId());

        startActivity(intent);
    }
}

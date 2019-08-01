package com.paulentine.android.capstone.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.paulentine.android.capstone.R;
import com.paulentine.android.capstone.model.Recipe;

/**
 * RecyclerView adapter for a list of Recipes from Search.
 */
public class SearchAdapter {

    public interface OnSearchSelectedListener {
        void onSearchSelected(DocumentSnapshot recipe);
    }

    private OnSearchSelectedListener mListener;

    public SearchAdapter(Query query, OnSearchSelectedListener listener) {
        super(query);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position), mListener);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        TextView readyInMinutesView;
        TextView servingsView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.recipe_item_title);
            readyInMinutesView = itemView.findViewById(R.id.recipe_item_ready_in_minutes);
            servingsView = itemView.findViewById(R.id.recipe_item_servings);
            imageView = itemView.findViewById(R.id.recipe_item_image);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnSearchSelectedListener listener) {

            Recipe recipe = snapshot.toObject(Recipe.class);
            Resources resources = itemView.getResources();

            titleView.setText(recipe.getTitle());
            readyInMinutesView.setText(Integer.toString(recipe.getReadyInMinutes()));
            servingsView.setText(Integer.toString(recipe.getServings()));

            // Load image
            Glide.with(imageView.getContext())
                    .load(recipe.getImage())
                    .into(imageView);

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onSearchSelected(snapshot);
                    }
                }
            });
        }

    }
}

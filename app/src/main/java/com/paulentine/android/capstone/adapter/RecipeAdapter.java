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
 package com.paulentine.android.capstone.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulentine.android.capstone.R;
import com.paulentine.android.capstone.model.Recipe;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

/**
 * RecyclerView adapter for a list of Recipes.
 */
public class RecipeAdapter extends FirestoreAdapter<RecipeAdapter.ViewHolder> {

    public interface OnRecipeSelectedListener {

        void onRecipeSelected(DocumentSnapshot recipe);

    }

    private OnRecipeSelectedListener mListener;

    public RecipeAdapter(Query query, OnRecipeSelectedListener listener) {
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

        TextView idView;
        TextView titleView;
        TextView readyInMinutesView;
        TextView servingsView;

//        ImageView imageView;

//        ImageView imageView;
//        TextView nameView;
//        MaterialRatingBar ratingBar;
//        TextView numRatingsView;
//        TextView priceView;
//        TextView categoryView;
//        TextView cityView;

        public ViewHolder(View itemView) {
            super(itemView);
            idView = itemView.findViewById(R.id.recipe_item_id);
            titleView = itemView.findViewById(R.id.recipe_item_title);
            readyInMinutesView = itemView.findViewById(R.id.recipe_item_helpertext_servings);
            servingsView = itemView.findViewById(R.id.recipe_item_helpertext_mins);
//            imageView = itemView.findViewById(R.id.recipe_item_image);

//            imageView = itemView.findViewById(R.id.recipe_item_image);
//            nameView = itemView.findViewById(R.id.recipe_item_title);
//            ratingBar = itemView.findViewById(R.id.recipe_item_rating);
//            numRatingsView = itemView.findViewById(R.id.recipe_item_num_ratings);
//            priceView = itemView.findViewById(R.id.recipe_item_id);
//            categoryView = itemView.findViewById(R.id.recipe_item_ready_in_minutes);
//            cityView = itemView.findViewById(R.id.recipe_item_servings);
        }

        public void bind(final DocumentSnapshot snapshot,
                         final OnRecipeSelectedListener listener) {

            Recipe recipe = snapshot.toObject(Recipe.class);
            Resources resources = itemView.getResources();

            idView.setText(Integer.toString(recipe.getId()));
            titleView.setText(recipe.getTitle());
            readyInMinutesView.setText(Integer.toString(recipe.getReadyInMinutes()));
            servingsView.setText(Integer.toString(recipe.getServings()));

//            // Load image
//            Glide.with(imageView.getContext())
//                    .load(recipe.getImage())
//                    .into(imageView);

//            nameView .setText(recipe.getTitle());
//            ratingBar.setRating((float) recipe.getAvgRating());
//            cityView.setText(recipe.getCity());
//            categoryView.setText(recipe.getCategory());
//            numRatingsView.setText(resources.getString(R.string.fmt_num_ratings,
//                    recipe.getNumRatings()));
//            priceView.setText(RecipeUtil.getPriceString(recipe));

            // Click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onRecipeSelected(snapshot);
                    }
                }
            });
        }

    }
}

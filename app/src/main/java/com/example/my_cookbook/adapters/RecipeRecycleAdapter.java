package com.example.my_cookbook.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_cookbook.R;
import com.example.my_cookbook.models.Recipe;

import java.util.List;

public class RecipeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recipe> mRecipes;
    private OnRecipeListener mOnRecipeListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_list_item, parent, false);
        return new RecipeViewHolder(view, mOnRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((RecipeViewHolder)holder).title.setText(mRecipes.get(position).getTitle());
        ((RecipeViewHolder)holder).publisher.setText(mRecipes.get(position).getPublisher());
        ((RecipeViewHolder)holder).socialScore.setText(String.valueOf(Math.round(mRecipes.get(position).getSocial_rank())));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
        notifyDataSetChanged();
    }
}

package com.example.my_cookbook.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.my_cookbook.models.Recipe;
import com.example.my_cookbook.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;

    public RecipeViewModel(){
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipeRepository.getRecipe();
    }

    public void searchRecipeById(String recipeId){
        mRecipeRepository.searchRecipeById(recipeId);
    }
}

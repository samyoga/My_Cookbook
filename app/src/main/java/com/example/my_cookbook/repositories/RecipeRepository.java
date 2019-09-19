package com.example.my_cookbook.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.my_cookbook.models.Recipe;
import com.example.my_cookbook.requests.RecipeApiClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private static RecipeApiClient mRecipeApiClient;

    public static RecipeRepository getInstance(){
        if (instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository(){
        mRecipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeApiClient.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber){
        if (pageNumber == 0){
            pageNumber = 1;
        }
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void cancelRequest(){
        mRecipeApiClient.cancelRequest();
    }
}

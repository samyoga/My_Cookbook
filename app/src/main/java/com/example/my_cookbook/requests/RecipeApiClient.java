package com.example.my_cookbook.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.my_cookbook.AppExecutors;
import com.example.my_cookbook.models.Recipe;

import java.util.List;
import java.util.concurrent.Future;

public class RecipeApiClient {

    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeApiClient getInstance(){
        if (instance == null){
            instance = new RecipeApiClient();
        }
        return instance;
    }

    private RecipeApiClient(){
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }

    public void searchRecipesApi(){
        final Future handler = AppExecutors.getInstance().getmNetworkIO().submit(new Runnable() {
            @Override
            public void run() {
                //retrieve data from REST API
//                mRecipes.postValue();
            }
        });
    }
}

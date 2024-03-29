package com.example.my_cookbook.requests;

import com.example.my_cookbook.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RecipeAPI recipeAPI = retrofit.create(RecipeAPI.class);

    public static RecipeAPI getRecipeAPI(){
        return recipeAPI;
    }

}

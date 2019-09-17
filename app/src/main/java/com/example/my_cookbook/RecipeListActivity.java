package com.example.my_cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.my_cookbook.models.Recipe;
import com.example.my_cookbook.requests.RecipeAPI;
import com.example.my_cookbook.requests.ServiceGenerator;
import com.example.my_cookbook.requests.responses.RecipeResponse;
import com.example.my_cookbook.requests.responses.RecipeSearchResponse;
import com.example.my_cookbook.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        test = findViewById(R.id.test);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               testRetrofitRequest();
            }
        });
    }

    private void testRetrofitRequest(){
        RecipeAPI recipeAPI = ServiceGenerator.getRecipeAPI();

//        Call<RecipeSearchResponse> recipeSearchResponseCall = recipeAPI.searchRecipe(
//                Constants.API_KEY,
//                "chicken breast",
//                "1"
//        );
//
//        recipeSearchResponseCall.enqueue(new Callback<RecipeSearchResponse>() {
//            @Override
//            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
//                Log.d(TAG, "onResponse" + response.toString());
//                if (response.code() == 200){
//                    Log.d(TAG, "onResponse" + response.body().toString());
//                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
//                    for (Recipe recipe: recipes){
//                        Log.d(TAG, recipe.getTitle());
//                    }
//                }
//                else {
//                    try {
//                        Log.d(TAG, "onResponse" + response.errorBody().string());
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
//
//            }
//        });

        Call<RecipeResponse> recipeSearchResponseCall = recipeAPI.getRecipe(
                Constants.API_KEY,
                "35382"
        );

        recipeSearchResponseCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d(TAG, "onResponse" + response.toString());
                if (response.code() == 200){
                    Log.d(TAG, "onResponse" + response.body().toString());
                    Recipe recipe = response.body().getRecipe();
                    Log.d(TAG, "onResponse:Retrieve" + recipe.toString());
                }
                else {
                    try {
                        Log.d(TAG, "onResponse" + response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
    }
}

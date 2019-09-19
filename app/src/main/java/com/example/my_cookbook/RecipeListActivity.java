package com.example.my_cookbook;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.my_cookbook.adapters.OnRecipeListener;
import com.example.my_cookbook.adapters.RecipeRecycleAdapter;
import com.example.my_cookbook.models.Recipe;
import com.example.my_cookbook.util.Testing;
import com.example.my_cookbook.util.VerticalSpacingItemDecorator;
import com.example.my_cookbook.viewmodels.RecipeListViewModel;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = "RecipeListActivity";
    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecycleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecyclerView = findViewById(R.id.recipe_list);
        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if (!mRecipeListViewModel.isViewingRecipes()){
            // display search categories
            displaySearchCategories();
        }
    }

    private void subscribeObservers(){
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes !=null){
                    if (mRecipeListViewModel.isViewingRecipes()){
                        Testing.printRecipes(recipes, "recipes: ");
                        mAdapter.setRecipes(recipes);
                        mRecipeListViewModel.setIsPerformingQuery(false);
                    }
                }

            }
        });
    }

    private void initRecyclerView(){
        mAdapter = new RecipeRecycleAdapter(this);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchRecipesApi(String query, int pageNumber){
        mRecipeListViewModel.searchRecipesApi(query, pageNumber);
    }

    private void initSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                mAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi(s, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(category, 1);
    }

    private void displaySearchCategories(){
        mRecipeListViewModel.setIsViewingRecipes(false);
        mAdapter.displaySearchCategories();
    }

    @Override
    public void onBackPressed() {
        if (mRecipeListViewModel.onBackPressed()){
            super.onBackPressed();
        } else{
            displaySearchCategories();
        }
    }
}

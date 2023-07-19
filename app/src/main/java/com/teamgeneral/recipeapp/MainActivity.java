package com.teamgeneral.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.teamgeneral.recipeapp.Adaptor.RandomRecipeAdapter;
import com.teamgeneral.recipeapp.Listeners.RandomRecipeResponseListener;
import com.teamgeneral.recipeapp.Listeners.RecipeClickListener;
import com.teamgeneral.recipeapp.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recycler_random;
    Spinner spinner_text;
    List<String> tags=new ArrayList<>();
    SearchView searchView_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading...");
        //spinner_text=(Spinner)findViewById(R.id.spinner_text);
        searchView_home=(SearchView)findViewById(R.id.searchView_home);
        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               tags.clear();
               tags.add(query);
               manager.getRandomRecipes(randomRecipeResponseListener);
               dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//

        manager=new RequestManager(this);
        manager.getRandomRecipes(randomRecipeResponseListener);
        dialog.show();
    }
    private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
           recycler_random=findViewById(R.id.recycler_random);
           recycler_random.setHasFixedSize(true);
           recycler_random.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
           randomRecipeAdapter=new RandomRecipeAdapter(MainActivity.this,response.recipes,recipeClickListener);
           recycler_random.setAdapter(randomRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

//
    private final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
//            Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,RecipeDetails.class)
                    .putExtra("id",id));

        }
    };
}
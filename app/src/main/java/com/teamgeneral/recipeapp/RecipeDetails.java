package com.teamgeneral.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.teamgeneral.recipeapp.Adaptor.IngredientsAdapter;
import com.teamgeneral.recipeapp.Adaptor.InstructionAdapter;
import com.teamgeneral.recipeapp.Adaptor.SimilarAdapter;
import com.teamgeneral.recipeapp.Listeners.InstructionListner;
import com.teamgeneral.recipeapp.Listeners.RecipeClickListener;
import com.teamgeneral.recipeapp.Listeners.RecipeDetailsListner;
import com.teamgeneral.recipeapp.Listeners.SimilarListner;
import com.teamgeneral.recipeapp.Models.InstructionResponse;
import com.teamgeneral.recipeapp.Models.RecipeDetailsModel;
import com.teamgeneral.recipeapp.Models.SimilarRecipeModel;

import org.w3c.dom.Text;

import java.util.List;

public class RecipeDetails extends AppCompatActivity {
    int id;
    TextView tv_recipe_name,tv_source_name,tv_summary;
    ImageView iv_recipe;
    RecyclerView rv_ingredients,rv_similar,rv_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarAdapter similarAdapter;
    InstructionAdapter instructionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();

        id =Integer.parseInt(getIntent().getStringExtra("id")) ;
        manager =new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListner,id);
        manager.getSimilarRecipe(similarListner,id);
        manager.getInstructions(instructionListner,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Details");
        dialog.show();

    }

    private void findViews() {
        tv_recipe_name=(TextView) findViewById(R.id.tv_recipe_name);
        tv_source_name=(TextView) findViewById(R.id.tv_source_name);
        tv_summary=(TextView) findViewById(R.id.tv_summary);
        iv_recipe=(ImageView) findViewById(R.id.iv_recipe);
        rv_ingredients=(RecyclerView) findViewById(R.id.rv_ingredients);
        rv_similar=findViewById(R.id.rv_similar);
        rv_instruction=findViewById(R.id.rv_instruction);

    }
    private final RecipeDetailsListner recipeDetailsListner=new RecipeDetailsListner() {
        @Override
        public void didFetch(RecipeDetailsModel recipeDetailsModel, String message) {
            dialog.dismiss();
            tv_recipe_name.setText(recipeDetailsModel.title);
            tv_source_name.setText(recipeDetailsModel.sourceName);
            tv_summary.setText(recipeDetailsModel.summary);
            Picasso.get().load(recipeDetailsModel.image).resize(1200,600).centerInside().into(iv_recipe);
            rv_ingredients.setHasFixedSize(true);
            rv_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetails.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter=new IngredientsAdapter(RecipeDetails.this,recipeDetailsModel.extendedIngredients);
            rv_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();

        }
    };
    private final SimilarListner similarListner=new SimilarListner() {
        @Override
        public void didFetch(List<SimilarRecipeModel> similarRecipeModels, String message) {
            rv_similar.setHasFixedSize(true);
            rv_similar.setLayoutManager(new LinearLayoutManager(RecipeDetails.this,LinearLayoutManager.HORIZONTAL,false));
            similarAdapter=new SimilarAdapter(RecipeDetails.this,similarRecipeModels,recipeClickListener);
            rv_similar.setAdapter(similarAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetails.this, message, Toast.LENGTH_SHORT).show();

        }
    };
    private  final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(RecipeDetails.this,RecipeDetails.class).putExtra("id",id));

        }
    };
    private InstructionListner instructionListner=new InstructionListner() {
        @Override
        public void didFetch(List<InstructionResponse> response, String message) {
            rv_instruction.setHasFixedSize(true);
            rv_instruction.setLayoutManager(new LinearLayoutManager(RecipeDetails.this,LinearLayoutManager.VERTICAL,false));
             instructionAdapter=new InstructionAdapter(RecipeDetails.this,response);
             rv_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}
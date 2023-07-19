package com.teamgeneral.recipeapp;

import android.content.Context;

import com.teamgeneral.recipeapp.Listeners.InstructionListner;
import com.teamgeneral.recipeapp.Listeners.RandomRecipeResponseListener;
import com.teamgeneral.recipeapp.Listeners.RecipeDetailsListner;
import com.teamgeneral.recipeapp.Listeners.SimilarListner;
import com.teamgeneral.recipeapp.Models.InstructionResponse;
import com.teamgeneral.recipeapp.Models.RandomRecipeApiResponse;
import com.teamgeneral.recipeapp.Models.RecipeDetailsModel;
import com.teamgeneral.recipeapp.Models.SimilarRecipeModel;
import com.teamgeneral.recipeapp.Models.Step;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }
    public void getRandomRecipes(RandomRecipeResponseListener listener){
        CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call =callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10");
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
               if(!response.isSuccessful()){
                   listener.didError(response.message());
                   return;

               }
               listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
               listener.didError(t.getMessage());
            }
        });
    }
    public  void getRecipeDetails(RecipeDetailsListner listner,int id){
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsModel> call=callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailsModel>() {
            @Override
            public void onResponse(Call<RecipeDetailsModel> call, Response<RecipeDetailsModel> response) {
                if(!response.isSuccessful()){
                    listner.didError(response.message());
                    return;
                }
                listner.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsModel> call, Throwable t) {
                listner.didError(t.getMessage());

            }
        });
    }
    public void getSimilarRecipe(SimilarListner similarListner,int id){
        CallSimilarRecipe callSimilarRecipe=retrofit.create(CallSimilarRecipe.class);
        Call<List<SimilarRecipeModel>> call=callSimilarRecipe.callSimilarRecipe(id,"4",context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeModel>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeModel>> call, Response<List<SimilarRecipeModel>> response) {
                if(!response.isSuccessful()){
                    similarListner.didError(response.message());
                    return;
                }
                similarListner.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeModel>> call, Throwable t) {
                similarListner.didError(t.getMessage());

            }
        });
    }
    public void getInstructions(InstructionListner instructionListner,int id){
        CallInstruction callInstruction =retrofit.create(CallInstruction.class);
        Call<List<InstructionResponse>> call=callInstruction.callInstruction(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<InstructionResponse>>() {
            @Override
            public void onResponse(Call<List<InstructionResponse>> call, Response<List<InstructionResponse>> response) {
                if(!response.isSuccessful()){
                    instructionListner.didError(response.message());
                    return;
                }
                instructionListner.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<InstructionResponse>> call, Throwable t) {
                instructionListner.didError(t.getMessage());

            }
        });

    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
                //@Query("tags")List<String> tags

                );

    }
    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsModel> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
    private interface CallSimilarRecipe{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeModel>> callSimilarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
                );
    }
    private interface CallInstruction{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<InstructionResponse>> callInstruction(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );

    }
}

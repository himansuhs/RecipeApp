package com.teamgeneral.recipeapp.Listeners;

import com.teamgeneral.recipeapp.Models.SimilarRecipeModel;

import java.util.List;

public interface SimilarListner {
    void didFetch(List<SimilarRecipeModel> similarRecipeModels,String message);
    void didError(String message);
}

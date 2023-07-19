package com.teamgeneral.recipeapp.Listeners;

import com.teamgeneral.recipeapp.Models.RecipeDetailsModel;

public interface RecipeDetailsListner {
    void didFetch(RecipeDetailsModel recipeDetailsModel,String message );
    void didError(String message);
}
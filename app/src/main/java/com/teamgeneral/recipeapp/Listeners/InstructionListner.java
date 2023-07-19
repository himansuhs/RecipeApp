package com.teamgeneral.recipeapp.Listeners;

import com.teamgeneral.recipeapp.Models.InstructionResponse;

import java.util.List;

public interface InstructionListner {
    void didFetch(List<InstructionResponse> response, String message);
    void didError(String message);
}

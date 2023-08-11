package com.example.recipegenerator.service;


import com.example.recipegenerator.model.request.RecipeRequest;
import com.example.recipegenerator.model.response.RecipeResponse;

import java.util.List;

public interface RecipeService {
    RecipeResponse addNewRecipe(RecipeRequest request);
    List<RecipeResponse> findRecipes(String id, String name, String mainIngredient);

}


package com.example.recipegenerator.service.impl;

import com.example.recipegenerator.entity.Recipe;
import com.example.recipegenerator.model.request.RecipeRequest;
import com.example.recipegenerator.model.response.RecipeResponse;
import com.example.recipegenerator.repository.RecipeRepository;
import com.example.recipegenerator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    @Override
    public RecipeResponse addNewRecipe(RecipeRequest request) {
        recipeRepository.addRecipe(request.getName(), request.getMainIngredient(),
                request.getAccompaniments(), request.getInstructions());

        String recipeId = UUID.randomUUID().toString();
        return RecipeResponse.builder()
                .recipeId(recipeId)
                .name(request.getName())
                .mainIngredient(request.getMainIngredient())
                .accompaniments(request.getAccompaniments())
                .instructions(request.getInstructions())
                .build();
    }

    @Override
    public List<RecipeResponse> findRecipes(String id, String name, String mainIngredient) {
        List<Recipe> recipes = recipeRepository.findRecipes(id, name, mainIngredient);

        return recipes.stream()
                .map(recipe ->
                        RecipeResponse.builder()
                                .recipeId(recipe.getRecipeId())
                                .name(recipe.getName())
                                .mainIngredient(recipe.getMainIngredient())
                                .accompaniments(recipe.getAccompaniments())
                                .instructions(recipe.getInstructions())
                                .userName(recipe.getAuthor().getName())
                                .build())
                .collect(Collectors.toList());
    }
}

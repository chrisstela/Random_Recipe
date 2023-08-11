package com.example.recipegenerator.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecipeResponse {
    private String recipeId;
    private String name;
    private String mainIngredient;
    private List<String> accompaniments;
    private String instructions;
    private String userName;
}

package com.example.recipegenerator.controller;

import com.example.recipegenerator.model.request.RecipeRequest;
import com.example.recipegenerator.model.response.CommonResponse;
import com.example.recipegenerator.model.response.RecipeResponse;
import com.example.recipegenerator.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeRequest request){
        RecipeResponse recipeResponse = recipeService.addNewRecipe(request);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully add a new recipe!")
                .data(recipeResponse)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getRecipes(@RequestParam(required = false) String id,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String mainIngredient) {
        List<RecipeResponse> recipeResponses = recipeService.findRecipes(id, name, mainIngredient);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Recipes retrieved successfully")
                .data(recipeResponses)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(commonResponse);
    }

}

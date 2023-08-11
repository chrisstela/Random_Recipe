package com.example.recipegenerator.repository;

import com.example.recipegenerator.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {
    @Modifying
    @Query(value = "INSERT INTO m_recipe (name, main_ingredient, accompaniments, instructions) " +
            "VALUES (:name, :mainIngredient, :accompaniments, :instructions)", nativeQuery = true)
    void addRecipe(@Param("name") String name, @Param("mainIngredient") String mainIngredient,
                   @Param("accompaniments") List<String> accompaniments, @Param("instructions") String instructions);

    @Query(value = "SELECT * FROM m_recipe WHERE id = :id OR name = :name OR main_ingredient = :mainIngredient", nativeQuery = true)
    List<Recipe> findRecipes(@Param("id") String id, @Param("name") String name, @Param("mainIngredient") String mainIngredient);
}

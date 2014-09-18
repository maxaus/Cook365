package com.baev.cook365.dao;

import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Recipe;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maxim Baev
 */
@Repository("ingredientDao")
public interface IngredientDao extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByRecipe(Recipe recipe);
}

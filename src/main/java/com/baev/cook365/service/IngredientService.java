package com.baev.cook365.service;

import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Recipe;

import java.util.List;

/**
 * Сервис для работы с ингредиентами.
 *
 * @author Maxim Baev
 */
public interface IngredientService {

	void save(Ingredient ingredient);

	void update(Ingredient ingredient);

	void delete(Ingredient ingredient);

	List<Ingredient> findAll();

	List<Ingredient> findByRecipe(Recipe recipe);

}

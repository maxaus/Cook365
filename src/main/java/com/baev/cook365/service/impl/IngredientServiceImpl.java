package com.baev.cook365.service.impl;

import com.baev.cook365.dao.IngredientDao;
import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link IngredientService}.
 *
 * @author Maxim Baev
 */
@Service("ingredientService")
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientDao ingredientDao;

	@Override
	@Transactional
	public void save(Ingredient ingredient) {
		ingredientDao.save(ingredient);
	}

	@Override
	@Transactional
	public void update(Ingredient ingredient) {
		ingredientDao.save(ingredient);
	}

	@Override
	@Transactional
	public void delete(Ingredient ingredient) {
		ingredientDao.delete(ingredient);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> findAll() {
		return ingredientDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ingredient> findByRecipe(Recipe recipe) {
		return ingredientDao.findByRecipe(recipe);
	}
}

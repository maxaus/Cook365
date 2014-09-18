package com.baev.cook365.service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.util.search.SearchCriterion;

/**
 * Сервис для работы с рецептами.
 *
 * @author Maxim Baev
 */
public interface RecipeService {

	void save(Recipe recipe);

	void update(Recipe recipe);

	void delete(Recipe recipe);

	List<Recipe> reindexAll();

	Recipe findById(Long id);

	List<Recipe> findAll();

	List<Recipe> findByDate(Date date);

	long countRecipes(List<SearchCriterion> searchCriteria);

	Page<Recipe> searchRecipes(String searchString, Pageable pageable);

	List<Recipe> getRecipes(List<SearchCriterion> searchCriteria);

	Page<Recipe> getRecipes(List<SearchCriterion> searchCriteria, Pageable pageable);
}

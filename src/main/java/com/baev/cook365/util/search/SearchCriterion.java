package com.baev.cook365.util.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.baev.cook365.model.Recipe;

/**
 * Интерфейс для различных критериев поиска рецептов.
 *
 * @author Maxim Baev
 */
public interface SearchCriterion {

	Predicate getPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}

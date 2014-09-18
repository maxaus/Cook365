package com.baev.cook365.util.search;

import java.util.List;
import javax.persistence.criteria.*;
import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Product;
import com.baev.cook365.model.Recipe;

/**
 * Критерий поиска рецептов по продуктам.
 *
 * @author Maxim Baev
 */
public class ProductSearchCriterion implements SearchCriterion {

	private List<Long> productIds;

	public ProductSearchCriterion(List<Long> productIds) {
		this.productIds = productIds;
	}

	@Override
	public Predicate getPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return root.<Recipe, Ingredient>joinList("ingredients", JoinType.LEFT).<Ingredient, Product>join("product").get("id").in(productIds);
	}
}

package com.baev.cook365.util.search;

import javax.persistence.criteria.*;
import com.baev.cook365.model.Recipe;

/**
 * Критерий поиска рецептов по типу.
 *
 * @author Maxim Baev
 */
public class TypeSearchCriterion  implements SearchCriterion {

	private String type;

	public TypeSearchCriterion(String type) {
		this.type = type;
	}

	@Override
	public Predicate getPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		Path<String> path = root.get("type");
		return builder.equal(path, type);
	}
}

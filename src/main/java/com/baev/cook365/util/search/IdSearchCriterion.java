package com.baev.cook365.util.search;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.baev.cook365.model.Recipe;

/**
 * Критерий поиска по ID.
 *
 * @author Maxim Baev
 */
public class IdSearchCriterion implements SearchCriterion {

	private List<Long> ids;

	public IdSearchCriterion(List<Long> ids) {
		this.ids = ids;
	}

	@Override
	public Predicate getPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return root.get("id").in(ids);
	}
}

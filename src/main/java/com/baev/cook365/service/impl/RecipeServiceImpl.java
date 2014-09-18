package com.baev.cook365.service.impl;

import com.baev.cook365.dao.RecipeDao;
import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.RecipeService;
import com.baev.cook365.util.search.SearchCriterion;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * Реализация {@link RecipeService}.
 *
 * @author Maxim Baev
 */
@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeDao recipeDao;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional
	public void save(Recipe recipe) {
		for (Ingredient ingredient : recipe.getIngredients()) {
			if (ingredient.getMeasureUnit().getId() == null) {
				ingredient.setMeasureUnit(null);
			}
			ingredient.setRecipe(recipe);
		}
		recipeDao.save(recipe);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		recipe = fullTextEntityManager.merge(recipe);
		fullTextEntityManager.index(recipe);
	}

	@Override
	@Transactional
	public void update(Recipe recipe) {
		for (Ingredient ingredient : recipe.getIngredients()) {
			if (ingredient.getMeasureUnit().getId() == null) {
				ingredient.setMeasureUnit(null);
			}
			ingredient.setRecipe(recipe);
		}
		recipeDao.save(recipe);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		recipe = fullTextEntityManager.merge(recipe);
		fullTextEntityManager.index(recipe);
	}

	@Override
	@Transactional
	public void delete(Recipe recipe) {
		recipe = recipeDao.saveAndFlush(recipe);
		recipeDao.delete(recipe);
	}

	@Override
	@Transactional
	public List<Recipe> reindexAll() {
		List<Recipe> recipes = recipeDao.findAll();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		for (Recipe recipe : recipes) {
			recipe = fullTextEntityManager.merge(recipe);
			fullTextEntityManager.index(recipe);
		}
		fullTextEntityManager.flushToIndexes();
		return recipes;
	}

	@Override
	@Transactional(readOnly = true)
	public Recipe findById(Long id) {
		return recipeDao.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> findAll() {
		return recipeDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> findByDate(Date date) {
		return recipeDao.findByDate(date);
	}

	@Override
	@Transactional(readOnly = true)
	public long countRecipes(List<SearchCriterion> searchCriteria) {
		return recipeDao.count(recipeSearchSpecification(searchCriteria));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Recipe> searchRecipes(String searchString, Pageable pageable) {
		if (StringUtils.isEmpty(searchString)) {
			return new PageImpl<Recipe>(new ArrayList<Recipe>(), pageable, 0);
		} else {
			Map<String, Float> boostPerField = new HashMap<String, Float>();
			boostPerField.put("description", 4f);
			boostPerField.put("products", 3f);
			boostPerField.put("title", 2f);
			boostPerField.put("shortDescription", 2f);
			boostPerField.put("type", 1f);

			String[] productFields = {"description", "products", "title", "shortDescription", "type"};

			QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31, productFields,
					new RussianAnalyzer(Version.LUCENE_31), boostPerField);
			parser.setAllowLeadingWildcard(true);
			Query luceneQuery;
			try {
				luceneQuery = parser.parse(searchString);
			} catch (ParseException e) {
				return new PageImpl<Recipe>(new ArrayList<Recipe>(), pageable, 0);
			}
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

			List<Recipe> queryRes = fullTextEntityManager.createFullTextQuery(luceneQuery,
					Recipe.class).getResultList();
			int pageNum = pageable.getPageNumber();
			int pageSize = pageable.getPageSize();

			if (queryRes != null && pageNum >= 0) {
				Set<Recipe> hashDistRes = new HashSet<Recipe>();
				hashDistRes.addAll(queryRes);
				queryRes = new ArrayList<Recipe>(hashDistRes);
			} else {
				queryRes = new ArrayList<Recipe>(0);
			}

			int size = queryRes.size();
			if (!queryRes.isEmpty()) {
				Collections.sort(queryRes);
				queryRes = queryRes.subList((pageNum * pageSize) < size ? pageNum * pageSize : size, ((pageNum + 1) * pageSize) < size ? (pageNum + 1) * pageSize : size);
			}
			return new PageImpl<Recipe>(queryRes, pageable, size);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recipe> getRecipes(List<SearchCriterion> searchCriteria) {
		return recipeDao.findAll(recipeSearchSpecification(searchCriteria));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Recipe> getRecipes(List<SearchCriterion> searchCriteria, Pageable pageable) {
		return recipeDao.findAll(recipeSearchSpecification(searchCriteria), pageable);
	}


	public static Specification<Recipe> recipeSearchSpecification(final List<SearchCriterion> searchCriteria) {
		return new Specification<Recipe>() {
			public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				query.distinct(true);
				Predicate[] predicates = new Predicate[searchCriteria.size()];
				for (int i = 0; i < searchCriteria.size(); i++) {
					predicates[i] = searchCriteria.get(i).getPredicate(root, query, builder);
				}
				return builder.and(predicates);
			}
		};
	}
}

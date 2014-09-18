package com.baev.cook365.controller;

import com.baev.cook365.form.ExtendedSearchForm;
import com.baev.cook365.form.SearchForm;
import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Product;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.ProductService;
import com.baev.cook365.service.RecipeService;
import com.baev.cook365.util.search.ProductSearchCriterion;
import com.baev.cook365.util.search.SearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контроллер поиска по рецептам.
 *
 * @author Maxim Baev
 */
@Controller
public class SearchController {

	private ProductService productService;

	private RecipeService recipeService;

	private static final int PAGE_SIZE = 20;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView simpleSearchEmpty() {
		return new ModelAndView("simple_search_results");
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView simpleSearch(@ModelAttribute("searchForm")
									 SearchForm searchForm, BindingResult result) {
		ModelAndView mav = new ModelAndView("simple_search_results");
		mav.addObject("searchString", searchForm.getSearchString());

		Pageable pageable = new PageRequest(searchForm.getPage(), PAGE_SIZE);
		Page<Recipe> page = recipeService.searchRecipes(searchForm.getSearchString(), pageable);
		mav.addObject("page", page);
		return mav;
	}

	@RequestMapping("/extended_search_form")
	public ModelAndView showExtendedSearchForm() {
		ModelAndView mav = new ModelAndView("extended_search");
		ExtendedSearchForm extendedSearchForm = new ExtendedSearchForm();
		Map<String, String> products = new HashMap<String, String>();
		List<Product> productList = productService.findAll();
		for (Product product : productList) {
			products.put(String.valueOf(product.getId()), product.getName());
		}
		extendedSearchForm.setProducts(products);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(new Ingredient());
		ingredients.add(new Ingredient());
		ingredients.add(new Ingredient());
		extendedSearchForm.setIngredients(ingredients);

		mav.addObject("extendedSearchForm", extendedSearchForm);
		return mav;
	}

	@RequestMapping(value = "/extended_search_results", method = RequestMethod.GET)
	public ModelAndView extendedSearchEmpty() {
		return new ModelAndView("extended_search_results");
	}

	@RequestMapping(value = "/extended_search_results", method = RequestMethod.POST)
	public ModelAndView extendedSearch(@ModelAttribute("extendedSearchForm")
									   ExtendedSearchForm extendedSearchForm, BindingResult result) {
		ModelAndView mav = new ModelAndView("extended_search_results");
		List<Ingredient> ingredients = extendedSearchForm.getIngredients();
		List<Long> productIds = new ArrayList<Long>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getProduct() != null && ingredient.getProduct().getId() != null) {
				productIds.add(ingredient.getProduct().getId());
			}
		}
		Page<Recipe> page = null;
		if (!productIds.isEmpty()) {
			List<SearchCriterion> searchCriteria = new ArrayList<SearchCriterion>(1);
			searchCriteria.add(new ProductSearchCriterion(productIds));

			Pageable pageable = new PageRequest(extendedSearchForm.getPageNumber(), PAGE_SIZE);
			page = recipeService.getRecipes(searchCriteria, pageable);

			List<Ingredient> emptyIngredients = new ArrayList<Ingredient>();
			for (Ingredient ingredient : ingredients) {
				if (ingredient.getProduct() == null || ingredient.getProduct().getId() == null) {
					emptyIngredients.add(ingredient);
				}
			}
			ingredients.removeAll(emptyIngredients);
		}
		extendedSearchForm.setIngredients(ingredients);
		Map<String, String> products = new HashMap<String, String>();
		List<Product> productList = productService.findAll();
		for (Product product : productList) {
			products.put(String.valueOf(product.getId()), product.getName());
		}
		extendedSearchForm.setProducts(products);
		mav.addObject("extendedSearchForm", extendedSearchForm);
		mav.addObject("page", page);
		return mav;
	}
}

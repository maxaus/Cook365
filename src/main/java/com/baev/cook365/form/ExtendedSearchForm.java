package com.baev.cook365.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baev.cook365.model.Ingredient;

/**
 * Объект, представляющий расширенную форму поиска.
 *
 * @author Maxim Baev
 */
public class ExtendedSearchForm {

	/**
	 * Продукты, по которым ищутся рецепты.
	 */
	private Map<String, String> products = new HashMap<String, String>();

	/**
	 * Ингредиенты.
	 */
	private List<Ingredient> ingredients;

	/**
	 * Номер страницы результатов.
	 */
	private int pageNumber = 0;

	public Map<String, String> getProducts() {
		return products;
	}

	public void setProducts(Map<String, String> products) {
		this.products = products;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}

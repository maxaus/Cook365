package com.baev.cook365.form;

import com.baev.cook365.model.Recipe;
import com.baev.cook365.model.RecipeTypes;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Объект, представляющий совокупность полей формы редактирования рецепта.
 *
 * @author Maxim Baev
 */
public class EditRecipeForm implements Serializable {

	private static final long serialVersionUID = -8239648922343466746L;

	/**
	 * Название блюда.
	 */
	private String title;

	/**
	 * Рецепт.
	 */
	private Recipe recipe;

	/**
	 * Дата, за которую будет показан рецепт.
	 */
	private String date;

	/**
	 * Типы блюд.
	 */
	private static final Map<String, String> types;

	/**
	 * Единицы измерения ингредиентов.
	 */
	private Map<String, String> measureUnits;

	/**
	 * Используемые в блюде продукты.
	 */
	private Map<String, String> products = new HashMap<String, String>();

	static {
		types = new HashMap<String, String>();
		types.put(RecipeTypes.BREAKFAST, "Завтрак");
		types.put(RecipeTypes.DINNER, "Обед");
		types.put(RecipeTypes.SUPPER, "Ужин");
		types.put(RecipeTypes.LUNCH, "Ланч");
		types.put(RecipeTypes.DESSERT, "Десерт");
		types.put(RecipeTypes.SALAD, "Салат");
		types.put(RecipeTypes.COCKTAIL, "Коктейль");
		types.put(RecipeTypes.GRILL, "Блюдо на огне");
	}

	/**
	 * Главное изображение для рецепта.
	 */
	private MultipartFile imageFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, String> getTypes() {
		return types;
	}

	public Map<String, String> getMeasureUnits() {
		return measureUnits;
	}

	public void setMeasureUnits(Map<String, String> measureUnits) {
		this.measureUnits = measureUnits;
	}

	public Map<String, String> getProducts() {
		return products;
	}

	public void setProducts(Map<String, String> products) {
		this.products = products;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}

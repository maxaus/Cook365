package com.baev.cook365.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ингредиент рецепта, представляет из себя связь между рефептом и продуктом с указанием единицы измерения и количеством.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "ingredient")
public class Ingredient extends AbstractEntity {

	private static final long serialVersionUID = 9144909273949727592L;

	/**
	 * Количество продукта.
	 */
	private float value;

	/**
	 * Единица измерения.
	 */
	@ManyToOne
	@JoinColumn(name = "measureUnitId")
	private MeasureUnit measureUnit;

	/**
	 * Рецепт.
	 */
	@ManyToOne
	@JoinColumn(name = "recipeId")
	private Recipe recipe;

	/**
	 * Продукт.
	 */
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public MeasureUnit getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(MeasureUnit measureUnit) {
		this.measureUnit = measureUnit;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

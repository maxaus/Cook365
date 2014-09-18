package com.baev.cook365.model;


import com.baev.cook365.util.ProductStringBridge;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Рецепт.
 *
 * @author Maxim Baev
 */
@Entity
@Indexed(index = "Recipes")
@Analyzer(impl = RussianAnalyzer.class)
@Table(name = "recipe")
public class Recipe extends AbstractEntity implements Comparable<Recipe> {

	private static final long serialVersionUID = -1771124987676345021L;

	/**
	 * Название.
	 */
	@Field(index = Index.TOKENIZED)
	private String title;

	/**
	 * Краткое описание.
	 */
	@Field(index = Index.TOKENIZED)
	private String shortDescription;

	/**
	 * Подробное описание.
	 */
	@Field(index = Index.TOKENIZED)
	private String description;

	/**
	 * Дата, при нажатии на которую, рецепт будет отображаться в списке.
	 */
	private Date date;

	/**
	 * Путь до главного изображения для рецепта.
	 */
	private String imagePath;

	/**
	 * Тип изображения.
	 */
	private String imageType;

	/**
	 * Ссылка на сайт, откуда был заимствован рецепт.
	 */
	private String externalLink;

	/**
	 * Является ли блюдо вегетарианским.
	 */
	private boolean vegetarian;

	/**
	 * Проверен ли рцепт администратором.
	 */
	private boolean moderated;

	/**
	 * Тип блюда.
	 */
	@Field(index = Index.TOKENIZED)
	private String type;

	/**
	 * Ингредиенты.
	 */
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Ingredient> ingredients;

	/**
	 * Продукты.
	 */
	@ManyToMany(targetEntity = Product.class)
	@Field(index = Index.TOKENIZED)
	@FieldBridge(impl = ProductStringBridge.class)
	@JoinTable(name = "ingredient", joinColumns = @JoinColumn(name = "recipeId"), inverseJoinColumns = @JoinColumn(name = "productId"))
	private List<Product> products = new ArrayList<Product>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public int compareTo(Recipe o) {
		return this.getId().compareTo(o.getId());
	}
}

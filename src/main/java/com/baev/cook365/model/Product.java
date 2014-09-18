package com.baev.cook365.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Продукт.
 *
 * @author Maxim Baev
 */
@Entity
@Table(name = "product")
public class Product extends AbstractEntity {

    private static final long serialVersionUID = -8845319813977821163L;

	/**
	 * Название.
	 */
    private String name;

	/**
	 * Флаг, показывающий, жидкость ли это (надо для приведения к одной базовой единицу при отображении списка продуктов,
	 * которые необходимо купить для приготовления нескольких блюд).
	 */
	private boolean liquid;

	/**
	 * Рецепты блюд, в которых используется данный продукт.
	 */
    @ManyToMany(mappedBy = "products", targetEntity = Recipe.class, fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<Recipe>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public boolean isLiquid() {
		return liquid;
	}

	public void setLiquid(boolean liquid) {
		this.liquid = liquid;
	}

	public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

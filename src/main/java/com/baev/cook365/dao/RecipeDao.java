package com.baev.cook365.dao;

import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Product;
import com.baev.cook365.model.Recipe;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Maxim Baev
 */
@Repository("recipeDao")
public interface RecipeDao extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {

    List<Recipe> findByDate(Date date);

}

package com.baev.cook365.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.RecipeService;

/**
 * Контроллер для отображения страницы с детальной информацией о рецепте.
 *
 * @author Maxim Baev
 */
@Controller
public class RecipeDetailsController {

	@Autowired
	private RecipeService recipeService;

	@RequestMapping(value = "/recipe_details", method = RequestMethod.GET)
	public ModelAndView showRecipeDetails(@RequestParam(value = "recipeId", defaultValue = "0") long recipeId) {
		Recipe recipe = recipeService.findById(recipeId);
		if (recipe == null) {
			return new ModelAndView("/error/404");
		}
		return new ModelAndView("/recipe_details", "recipe", recipe);
	}
}

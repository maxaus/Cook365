package com.baev.cook365.controller;

import com.baev.cook365.model.Recipe;
import com.baev.cook365.model.RecipeTypes;
import com.baev.cook365.service.RecipeService;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Контроллер для отображения меню на день.
 *
 * @author Maxim Baev
 */
@Controller
public class DayMenuController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DayMenuController.class);

    private static final String DATE_PATTERN = "dd-MM-yyyy";

	@Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView showMenuForSelectedDate(@RequestParam("date") String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date date;
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return new ModelAndView("/error/400");
        }
        List<Recipe> recipes = recipeService.findByDate(date);
        List<Recipe> breakfastRecipes = new ArrayList<Recipe>();
        List<Recipe> dinnerRecipes = new ArrayList<Recipe>();
        List<Recipe> supperRecipes = new ArrayList<Recipe>();
        for (Recipe recipe: recipes) {
            if (RecipeTypes.BREAKFAST.equals(recipe.getType())) {
                breakfastRecipes.add(recipe);
            } else if (RecipeTypes.DINNER.equals(recipe.getType())) {
                dinnerRecipes.add(recipe);
            } else if (RecipeTypes.SUPPER.equals(recipe.getType())) {
                supperRecipes.add(recipe);
            }
        }
        ModelAndView mav = new ModelAndView("menu");
        mav.addObject("breakfastRecipes", breakfastRecipes);
        mav.addObject("dinnerRecipes", dinnerRecipes);
        mav.addObject("supperRecipes", supperRecipes);
        DateTime selectedDateTime = new DateTime(date.getTime());
        mav.addObject("day", selectedDateTime.getDayOfMonth());
        mav.addObject("month", selectedDateTime.getMonthOfYear());
        mav.addObject("year", selectedDateTime.getYear());
        return  mav;

    }
}

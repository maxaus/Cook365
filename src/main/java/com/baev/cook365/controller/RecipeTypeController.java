package com.baev.cook365.controller;

import com.baev.cook365.model.Recipe;
import com.baev.cook365.model.RecipeTypes;
import com.baev.cook365.service.RecipeService;
import com.baev.cook365.util.search.SearchCriterion;
import com.baev.cook365.util.search.TypeSearchCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для отображения страниц с рецетами, сгруппированными по типу блюда.
 *
 * @author Maxim Baev
 */
@Controller
public class RecipeTypeController {

    @Autowired
    private RecipeService recipeService;

    private static final int PAGE_SIZE = 10;

    @RequestMapping(value = "/" + RecipeTypes.BREAKFAST, method = RequestMethod.GET)
    public ModelAndView showBreakfast(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.BREAKFAST, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.DINNER, method = RequestMethod.GET)
    public ModelAndView showDinner(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.DINNER, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.SUPPER, method = RequestMethod.GET)
    public ModelAndView showSupper(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.SUPPER, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.SALAD, method = RequestMethod.GET)
    public ModelAndView showSalad(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.SALAD, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.COCKTAIL, method = RequestMethod.GET)
    public ModelAndView showCocktail(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.COCKTAIL, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.DESSERT, method = RequestMethod.GET)
    public ModelAndView showDessert(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.DESSERT, pageNum);
    }

    @RequestMapping(value = "/" + RecipeTypes.GRILL, method = RequestMethod.GET)
    public ModelAndView showGrill(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
        return createPageModelAndView(RecipeTypes.GRILL, pageNum);
    }

    private ModelAndView createPageModelAndView(String type, int pageNum) {
        Page<Recipe> page = getRecipePageByType(type, pageNum);
        ModelAndView mav = new ModelAndView("recipes_of_type");
        mav.addObject("page", page);
        mav.addObject("type", type);
        return mav;
    }

    private Page<Recipe> getRecipePageByType(String type, int pageNum) {
        List<SearchCriterion> searchCriteria = new ArrayList<SearchCriterion>(1);
        searchCriteria.add(new TypeSearchCriterion(type));
        Pageable pageable = new PageRequest(pageNum, PAGE_SIZE);
        return recipeService.getRecipes(searchCriteria, pageable);
    }
}

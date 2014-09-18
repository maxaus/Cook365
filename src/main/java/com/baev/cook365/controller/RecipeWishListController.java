package com.baev.cook365.controller;

import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.Product;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.ProductService;
import com.baev.cook365.service.RecipeService;
import com.baev.cook365.util.search.IdSearchCriterion;
import com.baev.cook365.util.search.SearchCriterion;
import com.baev.cook365.view.ExcelRecipeWishListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Контроллер для операций со списком блюд, которые пользователь хочет приготовить.
 *
 * @author Maxim Baev
 */
@Controller
@SessionAttributes("recipeIds")
public class RecipeWishListController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add_to_wish_list")
    public ModelAndView addToWishList(@RequestParam(value = "recipeId", defaultValue = "0") long recipeId, HttpSession session) {
        Set<Long> recipeIds = new HashSet<Long>();
        if (session.getAttribute("recipeIds") != null) {
            recipeIds = (Set<Long>) session.getAttribute("recipeIds");
        }

        recipeIds.add(recipeId);
        session.setAttribute("recipeIds", recipeIds);
        return new ModelAndView("modal/add_wish_list_result");
    }

    @RequestMapping(value = "/remove_from_wish_list")
    public ModelAndView removeFromWishList(@RequestParam(value = "recipeId", defaultValue = "0") long recipeId, HttpSession session) {
        Set<Long> recipeIds = new HashSet<Long>();
        if (session.getAttribute("recipeIds") != null) {
            recipeIds = (Set<Long>) session.getAttribute("recipeIds");
        }

        recipeIds.remove(recipeId);
        session.setAttribute("recipeIds", recipeIds);
        return new ModelAndView("modal/remove_from_wish_list_result");
    }

    @RequestMapping(value = "/recipe_wish_list")
    public ModelAndView showWishList(HttpSession session) {
        ModelAndView mav = new ModelAndView("recipe_wish_list");
        List<Recipe> recipes = null;
        if (session.getAttribute("recipeIds") != null) {
            Set<Long> recipeIdSet = (Set<Long>) session.getAttribute("recipeIds");
            if (!recipeIdSet.isEmpty()) {
                List<Long> recipeIds = new ArrayList<Long>(recipeIdSet);

                List<SearchCriterion> searchCriteria = new ArrayList<SearchCriterion>(1);
                searchCriteria.add(new IdSearchCriterion(recipeIds));
                recipes = recipeService.getRecipes(searchCriteria);

                Map<String, String> productMap = makeProductMap(recipes);
                mav.addObject("productMap", productMap);
            }
        }
        mav.addObject("recipes", recipes);
        return mav;
    }

    @RequestMapping(value = "/xls_wish_list")
    public ModelAndView exportWishListToExcel(HttpSession session) {
        List<Recipe> recipes = null;
        Map<String, String> productMap = null;
        if (session.getAttribute("recipeIds") != null) {
            Set<Long> recipeIdSet = (Set<Long>) session.getAttribute("recipeIds");
            List<Long> recipeIds = new ArrayList<Long>(recipeIdSet);
            List<SearchCriterion> searchCriteria = new ArrayList<SearchCriterion>(1);
            searchCriteria.add(new IdSearchCriterion(recipeIds));
            recipes = recipeService.getRecipes(searchCriteria);

            productMap = makeProductMap(recipes);

        }
        //return excel view
        Map<String, Object> model = new HashMap<String, Object>(1);
        model.put("productMap", productMap);
        return new ModelAndView(new ExcelRecipeWishListView(), model);
    }

    private Map<String, String> makeProductMap(List<Recipe> recipes) {
        Map<String, Float> ingredientMap = new HashMap<String, Float>();
        for (Recipe recipe : recipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getMeasureUnit() != null) {
                    String productName = ingredient.getProduct().getName();
                    Float value = ingredient.getValue();

                    if (ingredient.getMeasureUnit().getParent() != null) {
                        value = ingredient.getValue() * ingredient.getMeasureUnit().getRatio();
                    }
                    if (ingredientMap.containsKey(productName) && ingredient.getMeasureUnit().getRatio() != 0) {
                        ingredientMap.put(productName, ingredientMap.get(productName) + value);
                    } else {
                        ingredientMap.put(productName, ingredient.getMeasureUnit().getRatio() != 0 ? value : -value);
                    }
                }
            }
        }
        Map<String, String> productMap = new HashMap<String, String>(ingredientMap.size());
        for (Map.Entry<String, Float> entry : ingredientMap.entrySet()) {
            String productName = entry.getKey();
            Product product = productService.findByName(productName);
            if (entry.getValue() < 0) {
                productMap.put(productName, String.valueOf(-entry.getValue()) + " шт.");
            } else {
                productMap.put(productName, String.valueOf(entry.getValue()) + (product.isLiquid() ? " мл." : " гр."));
            }
        }
        return productMap;
    }
}

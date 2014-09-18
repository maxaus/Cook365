package com.baev.cook365.controller.admin;

import com.baev.cook365.form.EditRecipeForm;
import com.baev.cook365.model.Ingredient;
import com.baev.cook365.model.MeasureUnit;
import com.baev.cook365.model.Product;
import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.IngredientService;
import com.baev.cook365.service.MeasureUnitService;
import com.baev.cook365.service.ProductService;
import com.baev.cook365.service.RecipeService;
import com.baev.cook365.util.Constants;
import com.baev.cook365.util.validator.RecipeValidator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Контроллер для администрирования рецептов.
 *
 * @author Maxim Baev
 */
@Controller
public class RecipeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MeasureUnitService measureUnitService;

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    @RequestMapping("/admin/recipes")
    public ModelAndView showRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        ModelAndView model = new ModelAndView("admin/recipes");
        model.addObject("recipes", recipes);
        return model;

    }

    @RequestMapping(value = "/admin/edit_recipe", method = RequestMethod.GET)
    public ModelAndView showEditRecipeForm(@RequestParam("recipeId") String recipeIdStr) {
        EditRecipeForm recipeForm = new EditRecipeForm();

        Long recipeId = Long.valueOf(recipeIdStr);
        Recipe recipe = recipeService.findById(recipeId);
        recipeForm.setRecipe(recipe);

        Map<String, String> products = new HashMap<String, String>();
        List<Product> productList = productService.findAll();
        for (Product product : productList) {
            products.put(String.valueOf(product.getId()), product.getName());
        }
        recipeForm.setProducts(products);

        Map<String, String> measureUnits = new HashMap<String, String>();
        List<MeasureUnit> measureUnitList = measureUnitService.findAll();
        for (MeasureUnit unit : measureUnitList) {
            measureUnits.put(String.valueOf(unit.getId()), unit.getName());
        }
        recipeForm.setMeasureUnits(measureUnits);

        if (recipe.getDate() != null) {
            DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            recipeForm.setDate(dateFormat.format(recipe.getDate()));
        }
        return new ModelAndView("admin/edit_recipe", "recipeForm", recipeForm);
    }

    @RequestMapping(value = "/admin/add_recipe", method = RequestMethod.GET)
    public ModelAndView showAddRecipeForm() {
        EditRecipeForm recipeForm = new EditRecipeForm();

        Recipe recipe = new Recipe();
        recipe.setModerated(true);
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        Ingredient emptyIngredient = new Ingredient();
        ingredients.add(emptyIngredient);
        recipe.setIngredients(ingredients);
        recipeForm.setRecipe(recipe);

        Map<String, String> products = new HashMap<String, String>();
        List<Product> productList = productService.findAll();
        for (Product product : productList) {
            products.put(String.valueOf(product.getId()), product.getName());
        }
        recipeForm.setProducts(products);

        Map<String, String> measureUnits = new HashMap<String, String>();
        List<MeasureUnit> measureUnitList = measureUnitService.findAll();
        for (MeasureUnit unit : measureUnitList) {
            measureUnits.put(String.valueOf(unit.getId()), unit.getName());
        }
        recipeForm.setMeasureUnits(measureUnits);

        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        recipeForm.setDate(dateFormat.format(new Date()));

        return new ModelAndView("admin/edit_recipe", "recipeForm", recipeForm);
    }

    @RequestMapping(value = "/admin/edit_recipe", method = RequestMethod.POST)
    public ModelAndView editRecipe(@ModelAttribute("recipeForm")
                                   EditRecipeForm recipeForm, BindingResult result) {

        Recipe recipe = recipeForm.getRecipe();
        if (!recipeForm.getDate().isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            try {
                recipe.setDate(dateFormat.parse(recipeForm.getDate()));
            } catch (ParseException e) {
                LOGGER.error(e.getMessage());
            }
        }


        RecipeValidator validator = new RecipeValidator();
        validator.validate(recipe, result);
        if (result.hasErrors()) {
            return new ModelAndView("admin/edit_recipe");
        }
        if (recipeForm.getImageFile() != null && !recipeForm.getImageFile().isEmpty()) {
            try {
                MultipartFile img = recipeForm.getImageFile();
                String imgFileName = img.getOriginalFilename();
                recipe.setImageType(img.getContentType());
                recipe.setImagePath(imgFileName);
                File imgFile = new File(Constants.UPLOAD_PATH + imgFileName);
                FileUtils.writeByteArrayToFile(imgFile, img.getBytes());

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        if (recipe.getId() != null && recipe.getId() != 0) {
            Recipe savedRecipe = recipeService.findById(recipe.getId());
            if (savedRecipe.getImagePath() != null && (recipeForm.getImageFile() == null || recipeForm.getImageFile().isEmpty())) {
                recipe.setImagePath(savedRecipe.getImagePath());
                recipe.setImageType(savedRecipe.getImageType());
            }
            if (savedRecipe.getId() != null && savedRecipe.getImagePath() != null && recipeForm.getImageFile() != null && !recipeForm.getImageFile().isEmpty()) {
                File removedFile =
                        new File(Constants.UPLOAD_PATH + savedRecipe.getImagePath());
                boolean deleted = removedFile.delete();
                LOGGER.debug(removedFile.getPath() + " deleted: " + deleted);
            }
            recipeService.update(recipe);
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredientService.save(ingredient);
            }
        } else {
            recipeService.save(recipe);
        }


        List<Recipe> recipes = recipeService.findAll();
        ModelAndView model = new ModelAndView("admin/recipes");
        model.addObject("recipes", recipes);
        return model;
    }

    @RequestMapping(value = "/admin/delete_recipe", method = RequestMethod.GET)
    public ModelAndView deleteRecipe(@RequestParam("recipeId") String recipeIdStr) {
        Long recipeId = Long.valueOf(recipeIdStr);
        Recipe recipe = recipeService.findById(recipeId);
        recipeService.delete(recipe);

        List<Recipe> recipes = recipeService.findAll();
        ModelAndView model = new ModelAndView("admin/recipes");
        model.addObject("recipes", recipes);
        return model;
    }

    @RequestMapping(value = "/admin/reindex_recipes", method = RequestMethod.GET)
    public ModelAndView reindexRecipe() {
        List<Recipe> recipes = recipeService.reindexAll();
        ModelAndView model = new ModelAndView("admin/recipes");
        model.addObject("recipes", recipes);
        return model;
    }
}

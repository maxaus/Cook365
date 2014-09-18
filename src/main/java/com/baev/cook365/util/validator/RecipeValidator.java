package com.baev.cook365.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.baev.cook365.model.Recipe;

/**
 * Валидатор для рецептов.
 *
 * @author Maxim Baev
 */
public class RecipeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Recipe.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Recipe recipe = (Recipe) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recipe.title", "fill-in-the-name", "Надо заполнить");
    }
}


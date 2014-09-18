package com.baev.cook365.util;

import com.baev.cook365.model.Recipe;
import com.baev.cook365.service.RecipeService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Сервдет для отображения главного изображения для рецепта.
 *
 * @author Maxim Baev
 */
@Controller
public class GetImageServlet extends HttpServlet {

	private static final long serialVersionUID = 264407610893806145L;

	private static RecipeService recipeService;


	@Autowired
	public void setRecipeService(RecipeService recipeService) {
		GetImageServlet.recipeService = recipeService;
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String requestUri = req.getRequestURI();

		String recipeId = requestUri.substring(requestUri.lastIndexOf("/") + 1, requestUri.lastIndexOf("."));

		Recipe recipe = recipeService.findById(Long.valueOf(recipeId));
		if (recipe != null && recipe.getImagePath() != null && recipe.getImageType() != null) {

			resp.setContentType(recipe.getImageType());
			File imgFile = new File(Constants.UPLOAD_PATH + recipe.getImagePath());
			resp.getOutputStream().write(FileUtils.readFileToByteArray(imgFile));

		} else {
			resp.sendRedirect("/img/no-image.gif");
		}

	}
}
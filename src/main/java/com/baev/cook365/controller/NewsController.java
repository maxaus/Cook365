package com.baev.cook365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для отображения страницы новостей.
 *
 * @author Maxim Baev
 */
@Controller
public class NewsController {

	@RequestMapping("/news")
	public ModelAndView showNews() {
		return new ModelAndView("/news");
	}
}

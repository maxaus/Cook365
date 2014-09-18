package com.baev.cook365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для отображения страницы авторизации.
 *
 * @author Maxim Baev
 */
@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView welcome() {
		return new ModelAndView("login");
	}
}

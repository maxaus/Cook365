package com.baev.cook365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллера для отображения страниц при возникновении определённых ошибок.
 *
 * @author Maxim Baev
 */
@Controller
public class ErrorPageController {

	@RequestMapping("/400")
	public ModelAndView showIncorrectRequest(){
		return new ModelAndView("/error/400");
	}

	@RequestMapping("/404")
	public ModelAndView showNotFoundPage(){
		return new ModelAndView("/error/404");
	}

	@RequestMapping("/500")
	public ModelAndView showExceptionPage() {
		return new ModelAndView("/error/500");
	}
}

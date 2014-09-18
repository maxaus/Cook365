package com.baev.cook365.controller;

import com.baev.cook365.form.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * Контроллер для отображения заголовка.
 *
 * @author Maxim Baev
 */
@Controller
@SessionAttributes("recipeIds")
public class HeaderController {

    @RequestMapping("/header")
    public ModelAndView showHeader(HttpSession session) {
        ModelAndView mav = new ModelAndView("header");

        SearchForm searchForm = new SearchForm();
        mav.addObject("searchForm", searchForm);
        int wishListSize = 0;
        if (session.getAttribute("recipeIds") != null) {
            Set<Long> recipeIds = (Set<Long>) session.getAttribute("recipeIds");
            wishListSize = recipeIds.size();
        }
        mav.addObject("wishListSize", wishListSize);
        return mav;

    }
}

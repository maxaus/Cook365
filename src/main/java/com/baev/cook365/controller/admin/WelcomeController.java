package com.baev.cook365.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для отображения главной панели администратора.
 *
 * @author Maxim Baev
 */
@Controller
public class WelcomeController {

    @RequestMapping("/admin/home")
    public ModelAndView welcome() {
        return new ModelAndView("admin/home");
    }
}

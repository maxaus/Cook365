package com.baev.cook365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер для отображения информации о проекте.
 *
 * @author Maxim Baev
 */
@Controller
public class InfoController {


    @RequestMapping("/about_project")
    public ModelAndView showAboutProject() {
        return new ModelAndView("/about_project");
    }
}

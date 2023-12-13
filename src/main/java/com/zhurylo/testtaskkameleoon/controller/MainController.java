package com.zhurylo.testtaskkameleoon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/v1/main")
public class MainController {

    @GetMapping("/page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page");
        return modelAndView;
    }

}

package com.kuzmin.pizzaordering.controller;

import com.kuzmin.pizzaordering.domain.Pizza;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String showDesignForm(Model model) {
        return "home";
    }
}

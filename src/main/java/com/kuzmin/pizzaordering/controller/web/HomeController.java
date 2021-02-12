package com.kuzmin.pizzaordering.controller.web;

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
        model.addAttribute("main", "");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

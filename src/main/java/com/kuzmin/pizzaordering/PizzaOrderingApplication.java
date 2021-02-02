package com.kuzmin.pizzaordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PizzaOrderingApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(PizzaOrderingApplication.class, args);
    }
}

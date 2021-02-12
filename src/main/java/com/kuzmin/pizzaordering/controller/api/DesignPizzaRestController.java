package com.kuzmin.pizzaordering.controller.api;

import com.kuzmin.pizzaordering.domain.Pizza;
import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.repository.PizzaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/design",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignPizzaRestController {
    private final PizzaRepository pizzaRepository;

    public DesignPizzaRestController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/recent")
    public Iterable<Pizza> recentPizza() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return pizzaRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> pizzaById(@PathVariable("id") Long id) {
        Optional<Pizza> optPizza = pizzaRepository.findById(id);
        return optPizza.map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza postPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
}

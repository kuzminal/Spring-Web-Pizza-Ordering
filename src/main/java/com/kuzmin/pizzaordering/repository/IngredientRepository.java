package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.Ingredient;

import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}

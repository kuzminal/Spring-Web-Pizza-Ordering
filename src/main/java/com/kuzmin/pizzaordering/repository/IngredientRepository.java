package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

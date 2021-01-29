package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
}

package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.PizzaOrder;

public interface OrderRepository {
    PizzaOrder save(PizzaOrder order);
}

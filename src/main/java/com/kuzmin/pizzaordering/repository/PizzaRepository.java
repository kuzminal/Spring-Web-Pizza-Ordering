package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.Pizza;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PizzaRepository extends PagingAndSortingRepository<Pizza, Long> {

}

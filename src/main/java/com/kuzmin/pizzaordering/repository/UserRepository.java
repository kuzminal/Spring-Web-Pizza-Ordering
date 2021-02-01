package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
    List<PizzaOrder> findByDeliveryZip(String deliveryZip);
    List<PizzaOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);
    List<PizzaOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
    List<PizzaOrder> findAll(Pageable pageable);
}

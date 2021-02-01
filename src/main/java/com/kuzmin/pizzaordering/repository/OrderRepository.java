package com.kuzmin.pizzaordering.repository;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<PizzaOrder, String> {
    List<PizzaOrder> findByDeliveryZip(String deliveryZip);
    List<PizzaOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);
}

package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.domain.PizzaOrder;

public interface OrderMessagingService {

  void sendOrder(PizzaOrder order);
  
}

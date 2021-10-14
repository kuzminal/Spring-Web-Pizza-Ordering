package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;

public interface OrderMessagingService {

  void sendOrder(PizzaOrderDTO order);
  
}

package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;

public interface OrderReceiver {
    PizzaOrderDTO receiveOrder();
}

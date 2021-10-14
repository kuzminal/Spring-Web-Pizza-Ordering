package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.domain.PizzaOrder;

public interface OrderReceiver {
    PizzaOrder receiveOrder();
}

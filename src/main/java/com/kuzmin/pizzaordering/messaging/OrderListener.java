package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    public OrderListener() {
    }

    @JmsListener(destination = "pizzacloud.order.queue")
    public void receiveOrder(PizzaOrderDTO order) {
        System.out.println("Received order :" + order.getDeliveryName() + "form user : " + order.getUser().getFullname());
    }
}

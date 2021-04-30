package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    public PizzaOrderDTO receiveOrder() {
        return (PizzaOrderDTO) jms.receiveAndConvert("pizzacloud.order.queue");
    }
}

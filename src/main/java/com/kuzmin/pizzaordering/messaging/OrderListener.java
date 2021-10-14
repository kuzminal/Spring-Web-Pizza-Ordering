package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final Logger LOG = LoggerFactory.getLogger(OrderListener.class);

    public OrderListener() {
    }

    @KafkaListener(topics = "hqo5i9yy-tacocloud.orders.topic", groupId = "taco")
    public void receiveOrder(PizzaOrderDTO order) {
        LOG.info("Received order :" + order.getDeliveryName() + "form user : " + order.getUser().getFullname());
    }
}

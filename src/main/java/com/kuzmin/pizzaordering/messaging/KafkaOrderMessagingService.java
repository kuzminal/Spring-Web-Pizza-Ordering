package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate<String, PizzaOrderDTO> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(
            KafkaTemplate<String, PizzaOrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(PizzaOrderDTO order) {
        kafkaTemplate.sendDefault("key", order);
    }
}

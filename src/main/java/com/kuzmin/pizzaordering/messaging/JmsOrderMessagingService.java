package com.kuzmin.pizzaordering.messaging;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private final JmsTemplate jms;
    private final OrderService orderService;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, OrderService orderService) {
        this.jms = jms;
        this.orderService = orderService;
    }

    @Override
    public void sendOrder(PizzaOrder order) {
        jms.convertAndSend(
                "pizzacloud.order.queue",
                orderService.matToPizzaDto(order),
                this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        message.setJMSCorrelationID(UUID.randomUUID().toString());
        return message;
    }
}

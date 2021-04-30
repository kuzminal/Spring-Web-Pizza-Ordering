package com.kuzmin.pizzaordering.controller.web;

import com.kuzmin.pizzaordering.config.OrderProps;
import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.domain.User;
import com.kuzmin.pizzaordering.repository.OrderRepository;
import com.kuzmin.pizzaordering.messaging.JmsOrderMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepo;
    private final OrderProps props;
    private final JmsOrderMessagingService jmsOrderMessagingService;

    public OrderController(OrderRepository orderRepo, OrderProps props, JmsOrderMessagingService jmsOrderMessagingService) {
        this.orderRepo = orderRepo;
        this.props = props;
        this.jmsOrderMessagingService = jmsOrderMessagingService;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("pizzaOrder", new PizzaOrder());
        model.addAttribute("orders", "");
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("pizzaOrder") PizzaOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        jmsOrderMessagingService.sendOrder(order);
        sessionStatus.setComplete();
        return "redirect:/orders";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}

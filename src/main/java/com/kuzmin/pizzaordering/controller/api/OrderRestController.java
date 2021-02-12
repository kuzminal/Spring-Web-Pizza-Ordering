package com.kuzmin.pizzaordering.controller.api;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.domain.User;
import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import com.kuzmin.pizzaordering.repository.OrderRepository;
import com.kuzmin.pizzaordering.service.OrderService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderRestController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderRestController(OrderRepository orderRepository, OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @GetMapping(path = "/getUserOrder")
    public List<PizzaOrder> ordersForUser(
            @AuthenticationPrincipal User user, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @GetMapping
    public List<PizzaOrderDTO> getAllOrders(@RequestParam(required = false, defaultValue = "10") int pageSize) {
        return orderService.getAllOrders(pageSize);
    }

    @PutMapping(path="/{orderId}", consumes="application/json")
    public PizzaOrder putOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody PizzaOrder order) {
        order.setId(orderId);
        return orderRepository.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public PizzaOrder patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody PizzaOrder patch) {
        PizzaOrder order = orderRepository.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}

package com.kuzmin.pizzaordering.service;

import com.kuzmin.pizzaordering.domain.PizzaOrder;
import com.kuzmin.pizzaordering.domain.User;
import com.kuzmin.pizzaordering.model.PizzaOrderDTO;
import com.kuzmin.pizzaordering.model.UserDTO;
import com.kuzmin.pizzaordering.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<PizzaOrderDTO> ordersForUser(User user, int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);
        List<PizzaOrder> orders = orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
        return orders.stream()
                .map(this::matToPizzaDto)
                .collect(Collectors.toList());
    }

    public List<PizzaOrderDTO> getAllOrders(@RequestParam(required = false, defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);
        List<PizzaOrder> orders = orderRepository.findAll(pageable);
        return orders.stream()
                .map(this::matToPizzaDto)
                .collect(Collectors.toList());
    }

    public PizzaOrderDTO matToPizzaDto(PizzaOrder order) {
        PizzaOrderDTO dto = new PizzaOrderDTO();
        dto.setId(order.getId());
        dto.setPlacedAt(order.getPlacedAt());
        dto.setDeliveryName(order.getDeliveryName());
        dto.setDeliveryCity(order.getDeliveryCity());
        dto.setDeliveryStreet(order.getDeliveryStreet());
        dto.setDeliveryZip(order.getDeliveryZip());
        dto.setDeliveryState(order.getDeliveryState());
        dto.setCcCVV(order.getCcCVV());
        dto.setCcNumber(order.getCcNumber());
        dto.setCcExpiration(order.getCcExpiration());
        dto.setUser(mapToUserDto(order.getUser()));
        return dto;
    }

    public UserDTO mapToUserDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setFullname(user.getFullname());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }
}

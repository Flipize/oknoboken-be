package com.nilsson.oknoboken_be;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/submit")
    public Map<String, Object> handlePostRequest(@RequestBody Order order) {
        // Process the data (you can save it to a database, etc.)
        String name = order.getName();
        String email = order.getEmail();
        String phoneNumber = order.getPhoneNumber();

        return orderService.submitOrder(order);
    }
}


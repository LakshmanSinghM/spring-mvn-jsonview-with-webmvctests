package com.lakshman.springmvc_json.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.lakshman.springmvc_json.entity.Order;
import com.lakshman.springmvc_json.service.OrderService;
import com.lakshman.springmvc_json.view.Views;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/{userId}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @JsonView(Views.UserDetails.class)
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable Long userId) {

        return ResponseEntity.ok(orderService.getAllOrders(userId));
    }

    @GetMapping("/{orderId}")
    @JsonView(Views.UserDetails.class)
    public ResponseEntity<Order> getOrderById(@PathVariable Long userId, @PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.getOrderById(userId, orderId));
    }

    @PostMapping
    @JsonView(Views.UserDetails.class)
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @Valid @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(userId, order));
    }

    @PutMapping("/{orderId}")
    @JsonView(Views.UserDetails.class)
    public ResponseEntity<Order> updateOrder(@PathVariable Long userId, @PathVariable Long orderId,
            @Valid @RequestBody Order order) {
        return ResponseEntity.ok(
                orderService.updateOrder(userId, orderId, order));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        orderService.deleteOrder(userId, orderId);
        return ResponseEntity.noContent().build();
    }
}
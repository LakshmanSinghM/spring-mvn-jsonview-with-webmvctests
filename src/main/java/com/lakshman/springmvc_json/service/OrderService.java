package com.lakshman.springmvc_json.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lakshman.springmvc_json.entity.Order;
import com.lakshman.springmvc_json.entity.User;
import com.lakshman.springmvc_json.exception.ResourceNotFoundException;
import com.lakshman.springmvc_json.repository.OrderRepository;
import com.lakshman.springmvc_json.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<Order> getAllOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return user.getOrders();
    }

    public Order getOrderById(Long userId, Long orderId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Order does not belong to this user");
        }

        return order;
    }

    public Order createOrder(Long userId, Order orderReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setProductName(orderReq.getProductName());
        order.setTotal(orderReq.getTotal());
        order.setStatus(orderReq.getStatus());
        order.setUser(user);

        return orderRepository.save(order);
    }

    public Order updateOrder(Long userId, Long orderId, Order orderReq) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order existing = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!existing.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Order does not belong to this user");
        }

        existing.setProductName(orderReq.getProductName());
        existing.setTotal(orderReq.getTotal());
        existing.setStatus(orderReq.getStatus());

        return orderRepository.save(existing);
    }

    public void deleteOrder(Long userId, Long orderId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Order does not belong to this user");
        }

        orderRepository.delete(order);
    }
}
package com.lakshman.springmvc_json.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshman.springmvc_json.entity.Order;
import com.lakshman.springmvc_json.service.OrderService;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(101L);
        order.setProductName("Laptop");
        order.setTotal(1500.0);
        order.setStatus("SHIPPED");
    }

    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders(1L)).thenReturn(Arrays.asList(order));

        mockMvc.perform(get("/users/1/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(order.getId()))
                .andExpect(jsonPath("$[0].productName").value(order.getProductName()))
                .andExpect(jsonPath("$[0].total").value(order.getTotal()))
                .andExpect(jsonPath("$[0].status").value(order.getStatus()));
    }

    @Test
    void testGetOrderById() throws Exception {
        when(orderService.getOrderById(1L, 101L)).thenReturn(order);

        mockMvc.perform(get("/users/1/orders/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.productName").value(order.getProductName()))
                .andExpect(jsonPath("$.total").value(order.getTotal()))
                .andExpect(jsonPath("$.status").value(order.getStatus()));
    }

    @Test
    void testCreateOrder() throws Exception {
        when(orderService.createOrder(eq(1L), any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/users/1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.productName").value(order.getProductName()))
                .andExpect(jsonPath("$.total").value(order.getTotal()))
                .andExpect(jsonPath("$.status").value(order.getStatus()));
    }

    @Test
    void testUpdateOrder() throws Exception {
        when(orderService.updateOrder(eq(1L), eq(101L), any(Order.class))).thenReturn(order);

        mockMvc.perform(put("/users/1/orders/101")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.productName").value(order.getProductName()))
                .andExpect(jsonPath("$.total").value(order.getTotal()))
                .andExpect(jsonPath("$.status").value(order.getStatus()));
    }

    @Test
    void testDeleteOrder() throws Exception {
        mockMvc.perform(delete("/users/1/orders/101"))
                .andExpect(status().isNoContent());
    }
}

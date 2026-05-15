package com.lakshman.springmvc_json.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.lakshman.springmvc_json.view.Views;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetails.class)
    private Long id;

    @NotBlank(message = "Product name is required")
    @JsonView(Views.UserDetails.class)
    private String productName;

    @NotNull(message = "Total is required")
    @JsonView(Views.UserDetails.class)
    private Double total;

    @NotBlank(message = "Status is required")
    @JsonView(Views.UserDetails.class)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
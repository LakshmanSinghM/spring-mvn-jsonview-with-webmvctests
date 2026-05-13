package com.lakshman.springmvc_json.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.lakshman.springmvc_json.dto.Views;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetails.class)
    private Long id;

    @JsonView(Views.UserDetails.class)
    private String productName;

    @JsonView(Views.UserDetails.class)
    private Double total;

    @JsonView(Views.UserDetails.class)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

}
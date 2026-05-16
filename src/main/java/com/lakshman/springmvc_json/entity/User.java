package com.lakshman.springmvc_json.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.lakshman.springmvc_json.view.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserSummary.class)
    private Long id;

    @NotBlank(message = "Name is required")
    @JsonView(Views.UserSummary.class)
    private String name;

    @NotBlank(message = "Email is required")
    //  validation generally done at the DTO level to remove the mess code from the entity level
    @Email(message = "Email should be valid")
    @JsonView(Views.UserSummary.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // Once user deleted, its orders are also deleted (cascade type)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;
}
package com.lakshman.springmvc_json.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lakshman.springmvc_json.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
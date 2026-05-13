package com.lakshman.springmvc_json.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lakshman.springmvc_json.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
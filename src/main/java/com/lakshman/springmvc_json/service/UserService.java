package com.lakshman.springmvc_json.service;

import org.springframework.stereotype.Service;

import com.lakshman.springmvc_json.entity.User;
import com.lakshman.springmvc_json.exception.ResourceNotFoundException;
import com.lakshman.springmvc_json.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User createUser(User userReq) {
        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setName(userReq.getName());
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
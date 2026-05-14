package com.lakshman.springmvc_json.service;

import org.springframework.stereotype.Service;

import com.lakshman.springmvc_json.dto.ApiResponse;
import com.lakshman.springmvc_json.dto.UserRequestDto;
import com.lakshman.springmvc_json.entity.User;
import com.lakshman.springmvc_json.exception.ResourceNotFoundException;
import com.lakshman.springmvc_json.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setData(userRepository.findAll());
        apiResponse.setMessage("Fetched users successfully");
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    public ApiResponse<User> getUserById(Long id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setData(
                userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found")));
        apiResponse.setMessage("Fetched user successfully");
        apiResponse.setSuccess(true);
        return apiResponse;
    }

    public ApiResponse<User> createUser(UserRequestDto userReq) {
        User user = new User();
        user.setEmail(userReq.getEmail());
        user.setName(userReq.getName());

        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Saved user successfully");
        apiResponse.setSuccess(true);
        user = userRepository.save(user);
        apiResponse.setData(user);

        return apiResponse;
    }

    public ApiResponse<User> updateUser(Long id, UserRequestDto updatedUser) {

        User existing = getUserById(id).getData();
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        existing = userRepository.save(existing);

        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Updated user successfully");
        apiResponse.setSuccess(true);
        apiResponse.setData(existing);

        return apiResponse;
    }

    public ApiResponse<Void> deleteUser(Long id) {
        User user = getUserById(id).getData();
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Deleted user successfully with id " + id);
        apiResponse.setSuccess(true);
        apiResponse.setData(null);
        userRepository.delete(user);
        return apiResponse;
    }
}
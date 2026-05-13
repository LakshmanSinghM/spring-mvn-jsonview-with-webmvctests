package com.lakshman.springmvc_json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    String message;
    Boolean success;
    T data;
}
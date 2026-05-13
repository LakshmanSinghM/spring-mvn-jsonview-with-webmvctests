package com.lakshman.springmvc_json.dto;

import lombok.Data;

@Data
public class OrderRequestDto {
    private String productName;
    private Double total;
    private String status;
}

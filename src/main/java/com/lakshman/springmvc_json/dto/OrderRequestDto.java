package com.lakshman.springmvc_json.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderRequestDto {

    @NotBlank
    private String productName;
    @Min(value = 0)
    private Double total;
    private String status;
}
package com.eventdriven.microservice.rest.command;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {

    private String title;
    private BigDecimal price;
    private Integer quantity;
}

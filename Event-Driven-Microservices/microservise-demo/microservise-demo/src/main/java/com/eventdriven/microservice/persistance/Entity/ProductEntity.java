package com.eventdriven.microservice.persistance.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @Column(unique = true)
    //@GeneratedValue(strategy =GenerationType.IDENTITY)
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}

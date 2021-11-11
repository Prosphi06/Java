package com.car.rental.management.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This is the entity used to store data of the car being used for the requests
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CarId;
    @Column(name = "brand", nullable = false, length = 50)
    private String brand;
    @Column(name = "model", nullable = false, length = 50)
    private String model;
    //private Year year;
    private String color;
    private double price;
    private String image;


}

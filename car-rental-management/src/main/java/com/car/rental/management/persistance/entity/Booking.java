package com.car.rental.management.persistance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the entity used to store data of the bookings
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String pickupDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dropOffDate;
    @Column( nullable = false, length = 50)
    private Double deposit;
    @Column( nullable = false, length = 50)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "carId",referencedColumnName = "carId")
    private Car car;
}

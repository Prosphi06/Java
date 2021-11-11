package com.car.rental.management.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * This is the entity used to store data of the customer being used for the requests
 */
@Entity
@Data
@Table(name = "customer_details")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column( nullable = false, length = 50)
    private String firstName;
    @Column( nullable = false, length = 50)
    private String lastName;
    private String phone;

}

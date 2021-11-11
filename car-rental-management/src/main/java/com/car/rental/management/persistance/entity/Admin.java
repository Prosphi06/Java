package com.car.rental.management.persistance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This is the entity used to store admin details
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}

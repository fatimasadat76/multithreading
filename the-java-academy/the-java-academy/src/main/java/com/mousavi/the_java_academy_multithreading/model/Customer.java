package com.mousavi.the_java_academy_multithreading.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

@Entity
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false)
        private Date customerBirthDate;

        @Column(nullable = false)
        private String customerZipCode;

        @Column(nullable = false)
        private String customerAddress;

        @Column(nullable = false)
        private String customerSurname;

        @Column(nullable = false)
        private String customerName;

        @Column(nullable = false, unique = true)
        private String customerId;

        @Column(nullable = false, unique = true)
        private String recordNumber;

        // Getters and setters
    }


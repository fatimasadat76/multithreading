package com.mousavi.the_java_academy_multithreading.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

    @Entity
    public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false, unique = true)
        private String recordNumber;

        @Column(nullable = false, unique = true)
        private String accountNumber;

        @Column(nullable = false)
        private String customerId;

        @Column(nullable = false)
        private String accountType;

        @Column(nullable = false)
        private String customerName;

        @Column(nullable = false)
        private String accountCustomerId;

        @Column(nullable = false)
        private double accountLimit;

        @Column(nullable = false)
        private String customerZipCode;

        @Column(nullable = false)
        private Date accountOpenDate;

        @Column(nullable = false)
        private String customerNationalId;

        @Column(nullable = false)
        private double accountBalance;

        @ManyToOne
        @JoinColumn(nullable = false)
        private Customer customer;

        // Getters and setters
    }


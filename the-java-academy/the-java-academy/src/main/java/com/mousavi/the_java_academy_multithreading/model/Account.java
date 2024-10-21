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

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getRecordNumber() {
            return recordNumber;
        }

        public void setRecordNumber(String recordNumber) {
            this.recordNumber = recordNumber;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAccountCustomerId() {
            return accountCustomerId;
        }

        public void setAccountCustomerId(String accountCustomerId) {
            this.accountCustomerId = accountCustomerId;
        }

        public double getAccountLimit() {
            return accountLimit;
        }

        public void setAccountLimit(double accountLimit) {
            this.accountLimit = accountLimit;
        }

        public String getCustomerZipCode() {
            return customerZipCode;
        }

        public void setCustomerZipCode(String customerZipCode) {
            this.customerZipCode = customerZipCode;
        }

        public Date getAccountOpenDate() {
            return accountOpenDate;
        }

        public void setAccountOpenDate(Date accountOpenDate) {
            this.accountOpenDate = accountOpenDate;
        }

        public String getCustomerNationalId() {
            return customerNationalId;
        }

        public void setCustomerNationalId(String customerNationalId) {
            this.customerNationalId = customerNationalId;
        }

        public double getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
    }


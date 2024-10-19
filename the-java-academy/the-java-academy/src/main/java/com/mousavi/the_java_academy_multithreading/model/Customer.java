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

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public Date getCustomerBirthDate() {
                return customerBirthDate;
        }

        public void setCustomerBirthDate(Date customerBirthDate) {
                this.customerBirthDate = customerBirthDate;
        }

        public String getCustomerZipCode() {
                return customerZipCode;
        }

        public void setCustomerZipCode(String customerZipCode) {
                this.customerZipCode = customerZipCode;
        }

        public String getCustomerAddress() {
                return customerAddress;
        }

        public void setCustomerAddress(String customerAddress) {
                this.customerAddress = customerAddress;
        }

        public String getCustomerSurname() {
                return customerSurname;
        }

        public void setCustomerSurname(String customerSurname) {
                this.customerSurname = customerSurname;
        }

        public String getCustomerName() {
                return customerName;
        }

        public void setCustomerName(String customerName) {
                this.customerName = customerName;
        }

        public String getCustomerId() {
                return customerId;
        }

        public void setCustomerId(String customerId) {
                this.customerId = customerId;
        }

        public String getRecordNumber() {
                return recordNumber;
        }

        public void setRecordNumber(String recordNumber) {
                this.recordNumber = recordNumber;
        }
}


package com.mousavi.the_java_academy_multithreading.model;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.*;

public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date errorDate;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String recordNumber;

    @Column(nullable = false)
    private String errorCode;

    @Column(nullable = false)
    private String errorClassificationName;

    @Column(nullable = false)
    private String errorDescription;

    // Getters and setters
}


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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorClassificationName() {
        return errorClassificationName;
    }

    public void setErrorClassificationName(String errorClassificationName) {
        this.errorClassificationName = errorClassificationName;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}


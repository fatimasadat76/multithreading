package com.mousavi.the_java_academy_multithreading.service;


import com.mousavi.the_java_academy_multithreading.model.ErrorLog;
import com.mousavi.the_java_academy_multithreading.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogService {
    @Autowired
    private ErrorLogRepository errorLogRepository;

    public void logError(ErrorLog errorLog) {
        errorLogRepository.save(errorLog);
    }
}

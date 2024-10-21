package com.mousavi.the_java_academy_multithreading.repository;



import com.mousavi.the_java_academy_multithreading.model.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, UUID> {

}
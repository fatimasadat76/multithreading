package com.mousavi.the_java_academy_multithreading.repository;


        import com.mousavi.the_java_academy_multithreading.model.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    // شما می‌توانید متدهای جستجوی سفارشی اینجا اضافه کنید
}

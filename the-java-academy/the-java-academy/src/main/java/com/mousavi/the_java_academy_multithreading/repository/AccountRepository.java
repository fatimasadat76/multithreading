package com.mousavi.the_java_academy_multithreading.repository;



        import com.mousavi.the_java_academy_multithreading.model.Account;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
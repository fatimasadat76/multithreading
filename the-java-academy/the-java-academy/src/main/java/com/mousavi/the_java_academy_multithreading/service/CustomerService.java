package com.mousavi.the_java_academy_multithreading.service;



import com.mousavi.the_java_academy_multithreading.model.Customer;
import com.mousavi.the_java_academy_multithreading.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

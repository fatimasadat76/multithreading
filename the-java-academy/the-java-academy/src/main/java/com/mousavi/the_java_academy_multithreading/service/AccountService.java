package com.mousavi.the_java_academy_multithreading.service;

import com.mousavi.the_java_academy_multithreading.model.Account;
import com.mousavi.the_java_academy_multithreading.repository.AccountRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public void addAccount(Account account) {
        accountRepository.save(account);
    }
}


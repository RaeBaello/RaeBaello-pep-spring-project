package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    //Checks new user
    public int checkNewUser(Account account)
    {
        Optional<Account> optionalAccount = accountRepository.findByUsername(account.getUsername());
        if(optionalAccount.isPresent())
        {
            return 1;
        }
        if(account.getUsername().length() < 1 || account.getPassword().length() < 4)
        {
            return 2;
        }
        return 0;
    }

    //Registers new user
    public Account registerNewUser(Account account){
        return accountRepository.save(account);
    }

    //Verify user login.
    public Account verifyLogin(Account account)
    {
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(optionalAccount.isPresent())
        {
            return optionalAccount.get();
        }
        else{
            return null;
        }
    }

    



   }

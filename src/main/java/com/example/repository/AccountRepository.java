package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    //find account by username
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
     Optional<Account> findByUsername(String username);

    //find account by username and password
    @Query("SELECT a FROM Account a WHERE a.username = ?1 AND a.password = ?2")
     Optional<Account> findByUsernameAndPassword(String username, String password);
}


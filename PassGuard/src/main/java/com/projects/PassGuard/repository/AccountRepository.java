package com.projects.PassGuard.repository;

import com.projects.PassGuard.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface AccountRepository extends JpaRepository<Account, Long> {

    ArrayList<Account> findAccountsByIdNotNull();
}

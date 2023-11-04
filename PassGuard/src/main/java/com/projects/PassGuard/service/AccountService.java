package com.projects.PassGuard.service;


import com.projects.PassGuard.domain.Account;

import com.projects.PassGuard.dto.AccountDto;
import com.projects.PassGuard.exception.ResourceNotFoundException;
import com.projects.PassGuard.repository.AccountRepository;
import com.projects.PassGuard.utils.DateTimeHandler;
import com.projects.PassGuard.utils.EncryptionHandler;
import com.projects.PassGuard.utils.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService{

    @Autowired private AccountRepository repository;
    @Autowired private ModelMapper modelMapper;
    @Value("${security.salt}") private String salt;
    @Value("${security.key}") private String secretKey;

    public Response _insertAccount(AccountDto accountDto) {

        accountDto.setCreatedAt(DateTimeHandler.timeNow());
        accountDto.setPassword(EncryptionHandler.encrypt(accountDto.getPassword(), secretKey, salt));
        Account account = repository.save(mapToEntity(accountDto));
        return new Response.ResponseBuilder()
                .data(account)
                .message("[+] Account created successfully.")
                .status(HttpStatus.CREATED)
                .build();
    }

    public Response _getAccountById(Long id) {
        Account account = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Account", "id", id));
        return new Response.ResponseBuilder()
                .data(account)
                .message("[+] Account retrieved successfully.")
                .status(HttpStatus.FOUND)
                .build();
    }

    public Response _getPlainAccountById(Long id) {
        Account account = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Account", "id", id));
        account.setPassword(EncryptionHandler.decrypt(account.getPassword(), secretKey, salt));
        return new Response.ResponseBuilder()
                .data(account)
                .message("[+] Account retrieved successfully.")
                .status(HttpStatus.FOUND)
                .build();
    }

    public Response _getAccounts() {
        ArrayList<Account> accounts = repository.findAccountsByIdNotNull();
        return new Response.ResponseBuilder()
                .data(accounts)
                .message("[+] Accounts retrieved successfully.")
                .status(HttpStatus.FOUND)
                .build();
    }

    public Response _deleteAccountById(Long id) {
        Account account = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Account", "id", id));
        repository.deleteById(id);
        return new Response.ResponseBuilder()
                .data(account)
                .message("[+] Account deleted successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    public Response _updateAccountById(Long id, AccountDto accountDto) {
        Account account = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Account", "id", id));
        if(accountDto.getPassword() != null) account.setPassword(EncryptionHandler.encrypt(accountDto.getPassword(),secretKey, salt));
        if(accountDto.getUsername() != null) account.setUsername(accountDto.getUsername());
        if(accountDto.getPlatform() != null) account.setPlatform(accountDto.getPlatform());
        if(accountDto.getVault() != null) account.setVault(accountDto.getVault());
        account.setLastModification(DateTimeHandler.timeNow());
        account = repository.save(account);
        return new Response.ResponseBuilder()
                .data(account)
                .message("[+] Account updated successfully.")
                .status(HttpStatus.OK)
                .build();
    }

    ///////////////////////////////////////////////////////////////////////////////
    public Account mapToEntity(AccountDto accountDto){
        Account account = modelMapper.map(accountDto, Account.class);
        return account;
    }

    public AccountDto mapToDto(Account account){
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        return accountDto;
    }

}

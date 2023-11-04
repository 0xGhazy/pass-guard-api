package com.projects.PassGuard.controller;


import com.projects.PassGuard.dto.AccountDto;
import com.projects.PassGuard.service.AccountService;
import com.projects.PassGuard.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("")
    public ResponseEntity<?> createResource(@RequestBody AccountDto accountDto)
    {
        System.out.println(accountDto);
        Response response = service._insertAccount(accountDto);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveResource(@PathVariable Long id)
    {
        Response response = service._getAccountById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/{id}/plain")
    public ResponseEntity<?> retrievePlainResource(@PathVariable Long id)
    {
        Response response = service._getPlainAccountById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("")
    public ResponseEntity<?> retrieveResources()
    {
        Response response = service._getAccounts();
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable Long id)
    {
        Response response = service._deleteAccountById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateResource(@PathVariable Long id, @RequestBody AccountDto accountDto)
    {
        Response response = service._updateAccountById(id, accountDto);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }
}

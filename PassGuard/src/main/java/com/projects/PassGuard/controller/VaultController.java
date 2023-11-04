package com.projects.PassGuard.controller;

import com.projects.PassGuard.dto.VaultDto;
import com.projects.PassGuard.service.VaultService;
import com.projects.PassGuard.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vaults")
public class VaultController {

    @Autowired
    private VaultService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveResource(@PathVariable("id") Long id){
        Response response = service._getVaultById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("")
    public ResponseEntity<?> retrieveResources(){
        Response response = service._getAllVaults();
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PostMapping("")
    public ResponseEntity<?> createResource(@RequestBody VaultDto vaultDto){
        Response response = service._insertVault(vaultDto);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateResource(@RequestBody VaultDto vaultDto, @PathVariable("id") Long id){
        Response response = service._updateVault(vaultDto, id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable("id") Long id){
        Response response = service._deleteVault(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }
}

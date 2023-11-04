package com.projects.PassGuard.service;


import com.projects.PassGuard.domain.Account;
import com.projects.PassGuard.domain.Vault;
import com.projects.PassGuard.dto.VaultDto;
import com.projects.PassGuard.exception.DuplicationException;
import com.projects.PassGuard.exception.ResourceNotFoundException;
import com.projects.PassGuard.repository.VaultRepository;
import com.projects.PassGuard.utils.DateTimeHandler;
import com.projects.PassGuard.utils.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class VaultService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VaultRepository repository;

    public Response _getVaultById(Long id){
        Vault vault = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vault", "id", id));

        Response response = new Response.ResponseBuilder()
                .data(mapToDto(vault))
                .message("[+] Vault retrieved successfully")
                .status(HttpStatus.FOUND)
                .build();
        return response;
    }

    public Response _getAllVaults(){
        ArrayList<Vault> vaults = repository.findAllByIdIsNotNull();
        Response response = new Response.ResponseBuilder()
                .data(vaults)
                .message("[+] Vault retrieved successfully")
                .status(HttpStatus.FOUND)
                .build();
        return response;
    }

    public Response _insertVault(VaultDto vaultDto){
        Optional testVault = repository.findVaultByName(vaultDto.getName());
        if(testVault.isPresent()){
            throw new DuplicationException("Vault", "name", vaultDto.getName());
        }
        else
        {
            vaultDto.setCreatedAt(DateTimeHandler.timeNow());
            vaultDto.setLastModification(DateTimeHandler.timeNow());
        }
        Vault vault = repository.save(mapToEntity(vaultDto));
        Response response = new Response.ResponseBuilder()
                .data(vault)
                .message("[+] Vault inserted successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _updateVault(VaultDto vaultDto, Long id){
        Vault vault = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vault", "id", id));
        if(vaultDto.getName() != null) vault.setName(vaultDto.getName());
        if(vaultDto.getAccounts() != null)
        {
            ArrayList<Account> accounts = new ArrayList<>(vault.getAccounts());
            for (Account account: vaultDto.getAccounts()) {accounts.add(account);}
            vault.setAccounts(accounts);
        }
        vault.setLastModification(DateTimeHandler.timeNow());
        Response response = new Response.ResponseBuilder()
                .data(repository.save(vault))
                .message("[+] Vault updated successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _deleteVault(Long id){
        Vault testVault = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vault", "id", id));
        if( testVault.getAccounts().size() > 0 ){
            return new Response.ResponseBuilder()
                    .data(testVault)
                    .message("[!] Can't perform delete operation because vault have accounts")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        repository.deleteById(id);
        return new Response.ResponseBuilder()
                .data(testVault)
                .message("[+] Vault deleted successfully")
                .status(HttpStatus.OK)
                .build();
    }


    ///////////////////////////////////////////////////////////////////////////////
    public Vault mapToEntity(VaultDto vaultDto){
        Vault vault = modelMapper.map(vaultDto, Vault.class);
        return vault;
    }

    public VaultDto mapToDto(Vault vault){
        VaultDto vaultDto = modelMapper.map(vault, VaultDto.class);
        return vaultDto;
    }
}

package com.projects.PassGuard.service;

import com.projects.PassGuard.domain.Platform;
import com.projects.PassGuard.domain.Vault;
import com.projects.PassGuard.dto.PlatformDto;
import com.projects.PassGuard.dto.VaultDto;
import com.projects.PassGuard.exception.ResourceNotFoundException;
import com.projects.PassGuard.repository.PlatformRepository;
import com.projects.PassGuard.utils.DateTimeHandler;
import com.projects.PassGuard.utils.Response;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Component
public class PlatformService {

    @Autowired private PlatformRepository repository;
    @Autowired private ModelMapper modelMapper;


    public Response _insertPlatform(PlatformDto platformDto)
    {
        platformDto.setCreatedAt(DateTimeHandler.timeNow());
        Platform platform = repository.save(mapToEntity(platformDto));
        Response response = new Response.ResponseBuilder()
                .data(platform)
                .message("[+] Platform inserted successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _getPlatformById(Long id)
    {
        Platform platform = repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Platform", "id", id));
        Response response = new Response.ResponseBuilder()
                .data(platform)
                .message("[+] Platform retrieved successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _getAllPlatforms()
    {
        ArrayList<Platform> platforms = repository.findPlatformsByIdNotNull();
        Response response = new Response.ResponseBuilder()
                .data(platforms)
                .message("[+] Platforms retrieved successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _updatePlatform(PlatformDto platformDto, Long id)
    {
        Platform platform = repository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Platform", "id", id));
        if(platformDto.getAccounts() != null) platform.setAccounts(platformDto.getAccounts());
        if(platformDto.getName() != null) platform.setName(platformDto.getName());
        if(platformDto.getIconUri() != null) platform.setIconUri(platformDto.getIconUri());
        platform.setLastModification(DateTimeHandler.timeNow());
        Platform newPlatform = repository.save(platform);
        Response response = new Response.ResponseBuilder()
                .data(newPlatform)
                .message("[+] Platforms updated successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    public Response _deletePlatform(Long id)
    {
        Platform platform = repository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Platform", "id", id));
        repository.deleteById(id);
        Response response = new Response.ResponseBuilder()
                .data(platform)
                .message("[+] Platforms deleted successfully")
                .status(HttpStatus.OK)
                .build();
        return response;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public Platform mapToEntity(PlatformDto platformDto){
        Platform platform = modelMapper.map(platformDto, Platform.class);
        return platform;
    }

    public PlatformDto mapToDto(Platform platform){
        PlatformDto platformDto = modelMapper.map(platform, PlatformDto.class);
        return platformDto;
    }

}

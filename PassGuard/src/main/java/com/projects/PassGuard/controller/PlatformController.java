package com.projects.PassGuard.controller;

import com.projects.PassGuard.dto.PlatformDto;
import com.projects.PassGuard.service.PlatformService;
import com.projects.PassGuard.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/platforms")
public class PlatformController {


    @Autowired
    private PlatformService service;

    @PostMapping("")
    public ResponseEntity<?> createResource(@RequestBody PlatformDto platformDto)
    {
        Response response = service._insertPlatform(platformDto);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveResource(@PathVariable Long id)
    {
        Response response = service._getPlatformById(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @GetMapping("")
    public ResponseEntity<?> retrieveResources()
    {
        Response response = service._getAllPlatforms();
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateResource(@RequestBody PlatformDto platformDto, @PathVariable Long id)
    {
        Response response = service._updatePlatform(platformDto, id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable Long id)
    {
        Response response = service._deletePlatform(id);
        return new ResponseEntity<>(response.jsonfy(), response.getStatusCode());
    }

}

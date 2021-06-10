package com.cloudservices.testtask.controller;

import com.cloudservices.testtask.model.Application;
import com.cloudservices.testtask.service.ApplicationService;
import com.cloudservices.testtask.dto.ApplicationDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.cloudservices.testtask.dto.ApplicationDtoMapper.mapToApplicationDtos;

/*
 *Created on 09.06.2021
 */
@RestController
@Api("ApiController for Application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("/applications")
    public  ResponseEntity<List<ApplicationDto>> getApplications(@RequestParam(required = false) Integer page,
                                                                 Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(mapToApplicationDtos(applicationService.getApplications(pageNumber, sortDirection)),
                HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getSingleApplication(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.getSingleApplication(id),
                HttpStatus.OK);
    }

    @GetMapping("applications/history")
    public ResponseEntity<List<Application>> getApplicationsWithHistory(@RequestParam(required = false) Integer page,
                                                        Sort.Direction sort) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        return new ResponseEntity<>(applicationService.getAppWithHistory(pageNumber, sortDirection),
                HttpStatus.OK);
    }

    @PostMapping("applications")
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        if (application.getTitle().isEmpty() || application.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            applicationService.addApplication(application);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }



}

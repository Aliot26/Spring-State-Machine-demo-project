package com.cloudservices.testtask.controller;

import com.cloudservices.testtask.service.ApplicationService;
import com.cloudservices.testtask.dto.ApplicationDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ApplicationDto> getApplications(@RequestParam(required = false) int page, Sort.Direction sort) {
        int pageNumber = page >= 0 ? page : 0;
        return mapToApplicationDtos(applicationService.getApplications(pageNumber, sort));
    }


}

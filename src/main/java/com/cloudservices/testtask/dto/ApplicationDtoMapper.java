package com.cloudservices.testtask.dto;

import com.cloudservices.testtask.model.Application;

import java.util.List;
import java.util.stream.Collectors;

/*
 *Created on 09.06.2021
 */
public class ApplicationDtoMapper {
    private ApplicationDtoMapper() {
    }

    public static List<ApplicationDto> mapToApplicationDtos(List<Application> applications){
        return applications.stream()
                .map(app -> mapToApplicationDto(app))
                .collect(Collectors.toList());
    }

    private static ApplicationDto mapToApplicationDto(Application app) {
        return ApplicationDto.builder()
                .id(app.getId())
                .title(app.getTitle())
                .content(app.getContent())
                .status(app.getStatus())
                .build();
    }
}

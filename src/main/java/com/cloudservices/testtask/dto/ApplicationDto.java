package com.cloudservices.testtask.dto;

import com.cloudservices.testtask.model.AppStates;
import lombok.Builder;
import lombok.Getter;

/*
 *Created on 09.06.2021
 */
@Getter
@Builder
public class ApplicationDto {
    private Long id;
    private String title;
    private String content;
    private AppStates status;
}

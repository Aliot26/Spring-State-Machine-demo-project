package com.cloudservices.testtask.service;

import com.cloudservices.testtask.model.Application;
import org.springframework.data.domain.Sort;

import java.util.List;

/*
 *Created on 09.06.2021
 */
public interface ApplicationService {
    List<Application> getApplications(int page, Sort.Direction sort);
}

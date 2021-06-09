package com.cloudservices.testtask.service.impl;

import com.cloudservices.testtask.model.Application;
import com.cloudservices.testtask.repository.ApplicationRepository;
import com.cloudservices.testtask.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *Created on 09.06.2021
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private static final int PAGE_SIZE = 10;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<Application> getApplications(int page, Sort.Direction sort) {
        return applicationRepository.findAllApplications(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }
}

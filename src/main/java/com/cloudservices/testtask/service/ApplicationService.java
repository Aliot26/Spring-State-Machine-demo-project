package com.cloudservices.testtask.service;

import com.cloudservices.testtask.model.Application;
import org.springframework.data.domain.Sort;

import java.util.List;

/*
 *Created on 09.06.2021
 */
public interface ApplicationService {
    List<Application> getApplications(int page, Sort.Direction sort, String title);

    Application getSingleApplication(Long id);

    List<Application> getAppWithHistory(int pageNumber, Sort.Direction sort, String title);

    Application addApplication(Application application);

    Application updateApplication(Application application);

    Application replaceStatusApp(Application app);

    List<Application> getApplicationsByTitle(String title);
}

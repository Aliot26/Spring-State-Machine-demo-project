package com.cloudservices.testtask.service.impl;

import com.cloudservices.testtask.model.Application;
import com.cloudservices.testtask.model.EStatus;
import com.cloudservices.testtask.model.History;
import com.cloudservices.testtask.repository.ApplicationRepository;
import com.cloudservices.testtask.repository.HistoryRepository;
import com.cloudservices.testtask.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 *Created on 09.06.2021
 */
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private static final int PAGE_SIZE = 10;
    private final ApplicationRepository applicationRepository;
    private final HistoryRepository historyRepository;

    @Override
    public List<Application> getApplications(int page, Sort.Direction sort) {
        return applicationRepository.findAllApplications(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    @Override
    public Application getSingleApplication(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Application> getAppWithHistory(int page, Sort.Direction sort) {
        List<Application> allApps = applicationRepository.findAllApplications(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allApps.stream()
                .map(Application::getId)
                .collect(Collectors.toList());
        List<History> historyList = historyRepository.findAllByAppIdIn(ids);
        allApps.forEach(app -> app.setAppHistoryList(extractAppHistory(historyList, app.getId())));
        return allApps;
    }

    @Override
    public Application addApplication(Application application) {
        Application app = new Application();
        app.setTitle(application.getTitle());
        app.setContent(application.getContent());
        app.setStatus(EStatus.CREATED);

        app.getAppHistoryList()
                .add(History.builder()
                        .dateOfChange(LocalDateTime.now())
                        .status(EStatus.CREATED)
                        .build());
        return applicationRepository.save(app);
    }

//    @Override
//    public boolean changeStatus(EStatus status, Long id) {
//        Application app = getSingleApplication(id);
//        EStatus eStatus = app.getStatus();
//        if (eStatus.equals(EStatus.CREATED) && status.equals(EStatus.VERIFIED)) {
//            app.setStatus(status);
//        } else if (eStatus.equals(EStatus.VERIFIED) && status.equals(EStatus.ACCEPTED)) {
//            app.setStatus(status);
//        } else if (eStatus.equals(EStatus.ACCEPTED) && status.equals(EStatus.PUBLISHED)) {
//            app.setStatus(status);
//        } else if (eStatus.equals(EStatus.CREATED) && status.equals(EStatus.DELETED)) {
//            app.setStatus(status);
//        } else if (eStatus.equals(EStatus.VERIFIED) && status.equals(EStatus.REJECTED)) {
//            app.setStatus(status);
//        } else if (eStatus.equals(EStatus.ACCEPTED) && status.equals(EStatus.ACCEPTED)) {
//            app.setStatus(status);
//        }else {
//            return false;
//        }
//    }


    private List<History> extractAppHistory(List<History> appHistory, Long id) {
        return appHistory.stream()
                .filter(appLine -> appLine.getAppId().equals(id))
                .collect(Collectors.toList());
    }
}

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

    @Override
    public Application updateApplication(Application application) {
        Application app = getSingleApplication(application.getId());
        if (app.getStatus().equals(EStatus.CREATED)
                || app.getStatus().equals(EStatus.VERIFIED)) {
            app.setTitle(application.getTitle());
            app.setContent(application.getContent());

            return applicationRepository.save(app);
        }
        return null;
    }

    @Override
    public Application replaceStatusApp(Application app) {
        return applicationRepository.save(app);
    }

    private List<History> extractAppHistory(List<History> appHistory, Long id) {
        return appHistory.stream()
                .filter(appLine -> appLine.getAppId().equals(id))
                .collect(Collectors.toList());
    }
}

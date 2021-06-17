package com.cloudservices.testtask.service.impl;

import com.cloudservices.testtask.model.Application;
import com.cloudservices.testtask.model.EStatus;
import com.cloudservices.testtask.model.History;
import com.cloudservices.testtask.repository.ApplicationRepository;
import com.cloudservices.testtask.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {
    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    Application returnApp;

    @BeforeEach
    void setUp() {
        History returnHistory1 = History.builder()
                .id(1L)
                .appId(1L)
                .dateOfChange(LocalDateTime.now())
                .status(EStatus.CREATED)
                .build();
        History returnHistory2 = History.builder()
                .id(2L)
                .appId(1L)
                .dateOfChange(LocalDateTime.now())
                .status(EStatus.VERIFIED)
                .build();

        List<History> historyList = new ArrayList<>();
        historyList.add(returnHistory1);
        historyList.add(returnHistory2);

        returnApp = Application.builder()
                .id(1L)
                .title("Title")
                .content("Content")
                .status(EStatus.VERIFIED)
                .appHistoryList(historyList)
                .build();
    }

//    @Test
//    void getApplications() {
//        List<Application> appListReturned = new ArrayList<>();
//        appListReturned.add(returnApp);
//        appListReturned.add(Application.builder()
//                .id(2L)
//                .title("Title2")
//                .content("Content2")
//                .status(EStatus.CREATED)
//                .build());
//        when(applicationRepository
//                .findAllApplications(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "id"))))
//                .thenReturn(appListReturned);
//
//        List<Application> applicationList = applicationService.getApplications(0, Sort.Direction.ASC, );
//        assertNotNull(applicationList);
//        assertEquals(2, applicationList.size());
//    }

    @Test
    void getSingleApplication() {
        when(applicationRepository.findById(anyLong())).thenReturn(Optional.of(returnApp));
        Application app = applicationService.getSingleApplication(1l);
        assertNotNull(app);
        assertEquals(1, app.getId());
    }

    @Test
    void addApplication() {
        Application appToSave = Application.builder()
                .id(1L)
                .title("Title")
                .content("Content")
                .status(EStatus.CREATED)
                .build();

        when(applicationRepository.save(any())).thenReturn(returnApp);
        Application appSaved = applicationService.addApplication(appToSave);
        assertNotNull(appSaved);
        verify(applicationRepository).save(any());
     }
}

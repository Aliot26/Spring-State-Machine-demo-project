package com.cloudservices.testtask.controller;

import com.cloudservices.testtask.dto.ApplicationDto;
import com.cloudservices.testtask.model.Application;
import com.cloudservices.testtask.service.ApplicationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ApplicationDto>> getApplications(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
                                                                @RequestParam(required = false, value="title") String  title,
                                                                @RequestParam(required = false, value="status") String  status) {

        return new ResponseEntity<>(mapToApplicationDtos(applicationService.getApplications(pageNumber, sortDirection, title, status)),
                HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getSingleApplication(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.getSingleApplication(id),
                HttpStatus.OK);
    }

//    @GetMapping("applications/history")
//    public ResponseEntity<List<Application>> getApplicationsWithHistory(@RequestParam(defaultValue = "0") Integer pageNumber,
//                                                                        @RequestParam(defaultValue = "Sort.Direction.ASC") Sort.Direction sortDirection,
//                                                                        @RequestParam(required = false, value="title") String  title,
//                                                                        @RequestParam(required = false, value="status") String  status) {
//
//        return new ResponseEntity<>(applicationService.getAppWithHistory(pageNumber, sortDirection, title, status),
//                HttpStatus.OK);
//    }

    @GetMapping("applications/{title}")
    public ResponseEntity<List<Application>> getApplicationsByTitle(@RequestParam(value="title") String  title) {

        if(!title.isEmpty()){
            return new ResponseEntity<>(applicationService.getApplicationsByTitle(title), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("applications")
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        if (application.getTitle().isEmpty()
                || application.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(applicationService.addApplication(application), HttpStatus.CREATED);
        }
    }

    @PutMapping("applications/{id}")
    public ResponseEntity<Application> editApplication(@PathVariable Long id,
                                                       @RequestBody Application application) {
        Application updatedApp = applicationService.updateApplication(application);
        if (updatedApp != null) {
            return new ResponseEntity<>(updatedApp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("applications/{id}/event/{event}")
    public ResponseEntity<Application> changeStatus(@PathVariable Long id,
                                                    @PathVariable String event){
        applicationService.changeStatus(id, event);
        return null;
    }

//    @PutMapping("applications/status/{id}")
//    public ResponseEntity<Application> changeStatus(@PathVariable Long id,
//                                                    @RequestBody History history) {
//        Application app = applicationService.getSingleApplication(id);
//        EStatus prevStatus = app.getStatus();
//        EStatus nextStatus = history.getStatus();
//
//        if (historyService.addHistory(id, history, prevStatus, nextStatus) != null) {
//            app.setStatus(nextStatus);
//            if (app.getStatus().equals(EStatus.PUBLISHED)) {
//                app.setAppNumber(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
//            }
//            return new ResponseEntity<>(applicationService.replaceStatusApp(app), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}

package com.cloudservices.testtask.service;

import com.cloudservices.testtask.model.*;
import com.cloudservices.testtask.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *Created on 09.06.2021
 */
@Service
@RequiredArgsConstructor
public class ApplicationService {
    private static final int PAGE_SIZE = 10;
    private final ApplicationRepository applicationRepository;
    private final StateMachineFactory<AppStates, AppEvents> factory;


    public List<Application> getApplications(int page, Sort.Direction sort, String title, String status) {
        AppStates state = Stream.of(AppStates.values())
                .filter(s -> s.name().equals(status))
                .findAny()
                .orElse(null);
        return applicationRepository.findAllApplications(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")),
                title, state);
    }

    public Application getSingleApplication(Long id) {
        return applicationRepository.findById(id).orElseThrow();
    }



    public Application addApplication(Application application) {
        Application app = new Application();
        app.setTitle(application.getTitle());
        app.setContent(application.getContent());
        app.setStatus(AppStates.CREATED);
        return applicationRepository.save(app);
    }


    public Application updateApplication(Application application) {
        Application app = getSingleApplication(application.getId());
        if ((app.getStatus().equals(AppStates.CREATED))
                || (app.getStatus().equals(AppStates.VERIFIED))){
            app.setTitle(application.getTitle());
            app.setContent(application.getContent());

            return applicationRepository.save(app);
        }
        return null;
    }

    private StateMachine<AppStates, AppEvents> build(Long appId) {
        Application app = applicationRepository.findById(appId).orElse(null);
        String appIdKey = Long.toString(app.getId());
        StateMachine<AppStates, AppEvents> sm = this.factory.getStateMachine(appIdKey);


        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {
                        @Override
                        public void preStateChange(State<AppStates, AppEvents> state, Message<AppEvents> message, Transition<AppStates, AppEvents> transition, StateMachine<AppStates, AppEvents> stateMachine, StateMachine<AppStates, AppEvents> rootStateMachine) {
                            Optional.ofNullable(message).ifPresent(msg -> {
                                Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault("appId", -1))).ifPresent(
                                        appId1 -> {
                                            Application app1 = applicationRepository.findById(appId1).orElse(null);
                                            app1.setStatus(state.getId());
                                            applicationRepository.save(app1);
                                        }
                                );

                            });
                        }
                    });
                    sma.resetStateMachine(new DefaultStateMachineContext<AppStates, AppEvents>(app.getStatus(), null, null, null));
                });
        return sm;
    }


    public List<Application> getApplicationsByTitle(String title) {
        return applicationRepository.findAllByTitleContaining(title);
    }


}

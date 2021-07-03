package com.cloudservices.testtask.config;

import com.cloudservices.testtask.model.AppEvents;
import com.cloudservices.testtask.model.AppStates;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/*
 *Created on 29.06.2021
 */
@Log
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<AppStates, AppEvents> {
    @Override
    public void configure(
            StateMachineConfigurationConfigurer
                    <AppStates, AppEvents> config) throws Exception {

        StateMachineListenerAdapter<AppStates, AppEvents> adapter = new StateMachineListenerAdapter<AppStates, AppEvents>(){
            @Override
            public void stateChanged(State<AppStates, AppEvents> from, State<AppStates, AppEvents> to) {
                log.info(String.format("stateChanged(from: %s, to: %s )", from + "", to + ""));
                super.stateChanged(from, to);
            }
        };
        config.withConfiguration()
                .autoStartup(false)
                .listener(adapter);
    }

    @Override
    public void configure(
            StateMachineStateConfigurer<AppStates, AppEvents> states)
            throws Exception {

        states.withStates()
                .initial(AppStates.CREATED)
                .state(AppStates.VERIFIED)
                .state(AppStates.ACCEPTED)
                .end(AppStates.REJECTED)
                .end(AppStates.DELETED)
                .end(AppStates.PUBLISHED);
    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<AppStates, AppEvents> transitions)
            throws Exception {

        transitions.withExternal()
                .source(AppStates.CREATED).target(AppStates.VERIFIED).event(AppEvents.VERIFY)
                .and()
                .withExternal()
                .source(AppStates.CREATED).target(AppStates.DELETED).event(AppEvents.DELETE)
                .and()
                .withExternal()
                .source(AppStates.VERIFIED).target(AppStates.REJECTED).event(AppEvents.REJECT)
                .and()
                .withExternal()
                .source(AppStates.VERIFIED).target(AppStates.ACCEPTED).event(AppEvents.ACCEPT)
                .and()
                .withExternal()
                .source(AppStates.ACCEPTED).target(AppStates.REJECTED).event(AppEvents.REJECT)
                .and()
                .withExternal()
                .source(AppStates.ACCEPTED).target(AppStates.PUBLISHED).event(AppEvents.PUBLISH);
    }
}

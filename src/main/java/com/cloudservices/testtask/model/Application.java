package com.cloudservices.testtask.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *Created on 09.06.2021
 */
@Entity
@Setter
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private State state;
    @OneToMany
    @JoinColumn(name = "appId")
    private List<AppHistory> appHistoryList;

}

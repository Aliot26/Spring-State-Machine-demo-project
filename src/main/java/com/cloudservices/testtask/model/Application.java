package com.cloudservices.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private Long appNumber;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "appId")
    private List<History> appHistoryList = new ArrayList<>();

}

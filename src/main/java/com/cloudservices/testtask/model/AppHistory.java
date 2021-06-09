package com.cloudservices.testtask.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 *Created on 09.06.2021
 */
@Entity
@Setter
@Getter
public class AppHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_change")
    private LocalDateTime dateOfChange;

    private EStatus status;

    private String reason;

    private Long appNumber;

    private Long appId;
}

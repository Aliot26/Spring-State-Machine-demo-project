package com.cloudservices.testtask.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDateTime date;

    private State state;

    private String reason;

    private Long number;

    private Long appId;
}

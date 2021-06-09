package com.cloudservices.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 *Created on 09.06.2021
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_change")
    private LocalDateTime dateOfChange;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private String reason;

    private Long appNumber;

    private Long appId;


}

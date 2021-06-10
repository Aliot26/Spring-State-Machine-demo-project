package com.cloudservices.testtask.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 *Created on 09.06.2021
 */
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_change")
    private LocalDateTime dateOfChange;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    private String reason;

    private Long appId;

}

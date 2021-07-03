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

    private String status;

    private String reason;

    private Long appId;

    public AppStates getStatus(){
        return AppStates.valueOf(this.status);
    }

    public void setStatus(AppStates as){
        this.status = as.name();
    }
}

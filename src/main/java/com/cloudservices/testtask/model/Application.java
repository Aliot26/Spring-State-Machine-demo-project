package com.cloudservices.testtask.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *Created on 09.06.2021
 */
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String status;

    private Long appNumber;

//    @OneToMany(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "appId")
//    private List<History> appHistoryList = new ArrayList<>();

    public AppStates getStatus(){
        return AppStates.valueOf(this.status);
    }

    public void setStatus(AppStates as){
        this.status = as.name();
    }
}

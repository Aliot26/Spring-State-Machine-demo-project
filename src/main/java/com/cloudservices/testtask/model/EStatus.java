package com.cloudservices.testtask.model;

/*
 *Created on 09.06.2021
 */
public enum EStatus {
    CREATED("CREATED"),
    VERIFIED("VERIFIED"),
    ACCEPTED("ACCEPTED"),
    DELETED("DELETED"),
    REJECTED("REJECTED"),
    PUBLISHED("PUBLISHED");

    private String status;

    EStatus(String estatus) {
        this.status = estatus;
    }

    public String getStatus() {
        return status;
    }
}

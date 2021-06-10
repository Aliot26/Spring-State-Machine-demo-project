package com.cloudservices.testtask.service;

import com.cloudservices.testtask.model.EStatus;
import com.cloudservices.testtask.model.History;

/*
 *Created on 10.06.2021
 */
public interface HistoryService {
    History addHistory(Long id, History history, EStatus prev, EStatus next);


}

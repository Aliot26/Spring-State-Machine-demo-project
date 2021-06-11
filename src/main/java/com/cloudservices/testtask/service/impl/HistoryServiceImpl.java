package com.cloudservices.testtask.service.impl;

import com.cloudservices.testtask.model.EStatus;
import com.cloudservices.testtask.model.History;
import com.cloudservices.testtask.repository.HistoryRepository;
import com.cloudservices.testtask.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/*
 *Created on 10.06.2021
 */
@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public History addHistory(Long id, History history, EStatus prevStatus, EStatus nextStatus) {

        History line = History.builder()
                .appId(id)
                .dateOfChange(LocalDateTime.now())
                .status(nextStatus)
                .build();
        if (prevStatus.equals(EStatus.CREATED) && nextStatus.equals(EStatus.VERIFIED)) {
            return historyRepository.save(line);
        } else if (prevStatus.equals(EStatus.VERIFIED) && nextStatus.equals(EStatus.ACCEPTED)) {
            return historyRepository.save(line);
        } else if (prevStatus.equals(EStatus.ACCEPTED) && nextStatus.equals(EStatus.PUBLISHED)) {
            return historyRepository.save(line);
        } else if (prevStatus.equals(EStatus.CREATED) && nextStatus.equals(EStatus.DELETED)
                && !history.getReason().isEmpty()) {
            line.setReason(history.getReason());
            return historyRepository.save(line);
        } else if (prevStatus.equals(EStatus.VERIFIED) && nextStatus.equals(EStatus.REJECTED)
                && !history.getReason().isEmpty()) {
            line.setReason(history.getReason());
            return historyRepository.save(line);
        } else if (prevStatus.equals(EStatus.ACCEPTED) && nextStatus.equals(EStatus.REJECTED)
                && !history.getReason().isEmpty()) {
            line.setReason(history.getReason());
            return historyRepository.save(line);
        } else {
            return null;
        }
    }
}

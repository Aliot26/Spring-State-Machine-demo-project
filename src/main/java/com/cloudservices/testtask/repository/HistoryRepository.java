package com.cloudservices.testtask.repository;

import com.cloudservices.testtask.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Created on 09.06.2021
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByAppIdIn(List<Long> ids);
}

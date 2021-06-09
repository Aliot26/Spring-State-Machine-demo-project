package com.cloudservices.testtask.repository;

import com.cloudservices.testtask.model.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Created on 09.06.2021
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("select a from Application a")
    List<Application> findAllApplications(Pageable page);
}

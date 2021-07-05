package com.cloudservices.testtask.repository;

import com.cloudservices.testtask.model.AppStates;
import com.cloudservices.testtask.model.Application;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *Created on 09.06.2021
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("select a from Application a where (a.title = :title or :title is null or :title ='')" +
            " and (a.status = :status or :status is null or :status = '')")
    List<Application> findAllApplications(Pageable page, @Param("title") String title, @Param("status") String status);

    List<Application> findAllByTitleContaining(String title);
}

package com.dataox.scrapingjobs.dao;

import com.dataox.scrapingjobs.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobDAO extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
    Optional<Job> findByOrganizationUrl(String url);
}

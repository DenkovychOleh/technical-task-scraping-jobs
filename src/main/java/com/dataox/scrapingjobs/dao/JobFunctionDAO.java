package com.dataox.scrapingjobs.dao;

import com.dataox.scrapingjobs.entities.JobFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobFunctionDAO extends JpaRepository<JobFunction, Long> {
    Optional<JobFunction> findByName(String name);
}

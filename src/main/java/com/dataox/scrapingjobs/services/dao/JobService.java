package com.dataox.scrapingjobs.services.dao;

import com.dataox.scrapingjobs.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface JobService {
    Job findById(Long id);

    void deleteAll();

    Job save(Job job);

    List<Job> findAll(Specification<Job> specificationParam);

    void findByUrlOrSave(Job job);
}

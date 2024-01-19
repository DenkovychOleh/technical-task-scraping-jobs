package com.dataox.scrapingjobs.services.dao;

import com.dataox.scrapingjobs.entities.JobFunction;

import java.util.List;

public interface JobFunctionService {
    JobFunction findById(Long id);

    List<JobFunction> findAll();

    JobFunction findByName(String name);
}

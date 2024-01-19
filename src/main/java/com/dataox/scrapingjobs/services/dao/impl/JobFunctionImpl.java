package com.dataox.scrapingjobs.services.dao.impl;

import com.dataox.scrapingjobs.dao.JobFunctionDAO;
import com.dataox.scrapingjobs.entities.JobFunction;
import com.dataox.scrapingjobs.exceptions.EntityNotFoundException;
import com.dataox.scrapingjobs.services.dao.JobFunctionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobFunctionImpl implements JobFunctionService {

    private final JobFunctionDAO jobFunctionDAO;


    @Override
    public JobFunction findById(Long id) {
        return jobFunctionDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(JobFunction.class, "with ID : " + id));
    }

    @Override
    public List<JobFunction> findAll() {
        return jobFunctionDAO.findAll();
    }

    @Override
    public JobFunction findByName(String name) {
        return jobFunctionDAO.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(JobFunction.class, "by name : " + name));
    }
}

package com.dataox.scrapingjobs.services.dao.impl;

import com.dataox.scrapingjobs.dao.JobDAO;
import com.dataox.scrapingjobs.entities.Job;
import com.dataox.scrapingjobs.exceptions.EntityNotFoundException;
import com.dataox.scrapingjobs.services.dao.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JobServiceImpl implements JobService {

    private final JobDAO jobDAO;


    @Override
    public Job findById(Long id) {
        return jobDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Job.class, "with ID = " + id));
    }

    public void deleteAll() {
        jobDAO.deleteAll();
    }

    @Override
    public Job save(Job job) {
        return jobDAO.save(job);
    }

    @Override
    public List<Job> findAll(Specification<Job> specificationParam) {
        return jobDAO.findAll(specificationParam);
    }

    @Override
    public void findByUrlOrSave(Job job) {
        jobDAO.findByOrganizationUrl(job.getOrganizationUrl()).or(
                () -> Optional.ofNullable(save(job)));
    }
}

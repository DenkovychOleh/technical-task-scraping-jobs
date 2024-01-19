package com.dataox.scrapingjobs.services.impl;

import com.dataox.scrapingjobs.dto.JobsResponseDto;
import com.dataox.scrapingjobs.dto.SearchRequestDto;
import com.dataox.scrapingjobs.dto.clients.JobFunctionsResponseDto;
import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.entities.Job;
import com.dataox.scrapingjobs.services.ScrapingJobsService;
import com.dataox.scrapingjobs.services.dao.JobFunctionService;
import com.dataox.scrapingjobs.services.dao.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dataox.scrapingjobs.specifications.JobSpecification.hasJobFunctionId;
import static com.dataox.scrapingjobs.specifications.JobSpecification.sortBy;

@AllArgsConstructor
@Service
public class ScrapingJobsServiceImpl implements ScrapingJobsService {

    private final JobFunctionService jobFunctionService;
    private final JobService jobService;

    @Override
    public List<JobFunctionsResponseDto> getJobFunctions() {
        return jobFunctionService
                .findAll()
                .stream()
                .map(jobFunction -> JobFunctionsResponseDto.builder()
                        .id(jobFunction.getId())
                        .name(jobFunction.getName())
                        .build()).
                toList();
    }

    @Override
    public List<JobsResponseDto> getJobs(SearchRequestDto searchRequestDto) {
        List<Job> jobs = jobService.findAll(getSpecificationParam(searchRequestDto));
        return jobs.stream()
                .map(this::buildJobResponseDtoByJob)
                .toList();
    }

    private JobsResponseDto buildJobResponseDtoByJob(Job job) {
        return JobsResponseDto.builder()
                .organizationTitle(job.getOrganizationTitle())
                .organizationUrl(job.getOrganizationUrl())
                .positionName(job.getPositionName())
                .logo(job.getLogo())
                .tags(job.getTags())
                .jobFunction(job.getJobFunction().getName())
                .created(job.getCreated())
                .location(job.getLocation())
                .description(job.getDescription())
                .build();
    }

    private Specification<Job> getSpecificationParam(SearchRequestDto searchRequestDto) {
        return Specification.allOf(
                hasJobFunctionId(searchRequestDto.jobFunctionsId())
                        .and(sortBy(searchRequestDto.sortBy(), searchRequestDto.orderBy()))
        );
    }
}

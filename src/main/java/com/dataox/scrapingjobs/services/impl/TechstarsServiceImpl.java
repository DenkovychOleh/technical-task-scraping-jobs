package com.dataox.scrapingjobs.services.impl;

import com.dataox.scrapingjobs.clients.CLiClient;
import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.dto.clients.ResultDto;
import com.dataox.scrapingjobs.dto.clients.TagsResponseDto;
import com.dataox.scrapingjobs.entities.Job;
import com.dataox.scrapingjobs.entities.JobFunction;
import com.dataox.scrapingjobs.services.TechstarsService;
import com.dataox.scrapingjobs.services.dao.JobFunctionService;
import com.dataox.scrapingjobs.services.dao.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TechstarsServiceImpl implements TechstarsService {

    private final CLiClient cLiClient;

    private final JobFunctionService jobFunctionService;

    private final JobService jobService;

    @Transactional
    @Override
    public ResultsResponseDto getJobs(List<Long> jobFunctions) {
        List<String> jobFunctionNames = new ArrayList<>();

        if (jobFunctions == null) {
            jobFunctionNames.addAll(jobFunctionService
                    .findAll().stream()
                    .map(JobFunction::getName)
                    .toList());
        } else {
            for (Long jobFunction : jobFunctions) {
                jobFunctionNames.add(jobFunctionService.findById(jobFunction).getName());
            }
        }

        ResultsResponseDto jobs = cLiClient.getJobs(jobFunctionNames);

        List<ResultDto> results = jobs.results();
        for (ResultDto result : results) {
            jobService.findByUrlOrSave(buildJobByResultDto(result));
        }
        return jobs;
    }

    @Override
    public List<TagsResponseDto> getTags() {
        return cLiClient.getTags();
    }

    private Job buildJobByResultDto(ResultDto resultDto) {
        return Job.builder()
                .created(resultDto.createdAt())
                .location(resultDto.locations())
                .organizationUrl(resultDto.organizationUrl())
                .logo(resultDto.logoUrl())
                .organizationTitle(resultDto.organizationTitle())
                .tags(resultDto.organizationTags())
                .positionName(resultDto.positionName())
                .jobFunction(jobFunctionService.findByName(resultDto.laborFunction()))
                .build();
    }
}

package com.dataox.scrapingjobs.services;

import com.dataox.scrapingjobs.dto.JobsResponseDto;
import com.dataox.scrapingjobs.dto.SearchRequestDto;
import com.dataox.scrapingjobs.dto.clients.JobFunctionsResponseDto;
import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;

import java.util.List;

public interface ScrapingJobsService {
    List<JobFunctionsResponseDto> getJobFunctions();

    List<JobsResponseDto> getJobs(SearchRequestDto searchRequestDto);
}

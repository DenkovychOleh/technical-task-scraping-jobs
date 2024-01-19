package com.dataox.scrapingjobs.controllers;

import com.dataox.scrapingjobs.dto.JobsResponseDto;
import com.dataox.scrapingjobs.dto.clients.JobFunctionsResponseDto;
import com.dataox.scrapingjobs.dto.SearchRequestDto;
import com.dataox.scrapingjobs.services.ScrapingJobsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/scraping-jobs")
@RestController
public class ScrapingJobsController {

    private final ScrapingJobsService scrapingJobsService;

    @GetMapping("/job-functions")
    public ResponseEntity<List<JobFunctionsResponseDto>> getJobFunctions() {
        return ResponseEntity.ok(scrapingJobsService.getJobFunctions());
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobsResponseDto>> getJobs (@ModelAttribute SearchRequestDto searchRequestDto) {
        return ResponseEntity.ok(scrapingJobsService.getJobs(searchRequestDto));
    }
}

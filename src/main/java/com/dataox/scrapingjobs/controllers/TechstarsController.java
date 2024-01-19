package com.dataox.scrapingjobs.controllers;

import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.dto.clients.TagsResponseDto;
import com.dataox.scrapingjobs.services.TechstarsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/techstarts")
@RestController
public class TechstarsController {

    private final TechstarsService techstarsService;

    @GetMapping("/industry-tags")
    public ResponseEntity<List<TagsResponseDto>> getTags() {
        return ResponseEntity.ok(techstarsService.getTags());
    }

    @GetMapping("/jobs")
    public ResponseEntity<ResultsResponseDto> getJobs (@RequestParam (value = "job_function", required = false) List<Long> jobFunctionsId) {
        return ResponseEntity.ok(techstarsService.getJobs(jobFunctionsId));
    }
}

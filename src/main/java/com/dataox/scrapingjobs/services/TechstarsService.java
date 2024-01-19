package com.dataox.scrapingjobs.services;


import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.dto.clients.TagsResponseDto;

import java.util.List;

public interface TechstarsService {
    List<TagsResponseDto> getTags();
    ResultsResponseDto getJobs(List<Long> jobFunctions);
}

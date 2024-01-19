package com.dataox.scrapingjobs.clients;

import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.dto.clients.TagsResponseDto;

import java.util.List;

public interface CLiClient {
    List<TagsResponseDto> getTags();

    ResultsResponseDto getJobs(List<String> jobFunctions);
}

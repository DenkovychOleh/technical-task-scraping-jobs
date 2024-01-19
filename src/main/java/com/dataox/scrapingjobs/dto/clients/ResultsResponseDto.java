package com.dataox.scrapingjobs.dto.clients;

import lombok.Builder;

import java.util.List;

@Builder
public record ResultsResponseDto(
        List<ResultDto> results,
        Integer nbJobs
        ) {
}

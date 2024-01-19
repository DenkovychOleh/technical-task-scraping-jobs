package com.dataox.scrapingjobs.dto;


import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record JobsResponseDto(
        String positionName,
        String organizationUrl,
        String logo,
        String organizationTitle,
        String location,
        String description,
        String tags,
        LocalDateTime created,
        String jobFunction) {
}


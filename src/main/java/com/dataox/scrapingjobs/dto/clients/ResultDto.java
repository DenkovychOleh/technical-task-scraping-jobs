package com.dataox.scrapingjobs.dto.clients;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ResultDto(
        LocalDateTime createdAt,
        String locations,
        String organizationUrl,
        String logoUrl,
        String organizationTitle,
        String organizationTags,
        String positionName,
        String laborFunction
) {
}

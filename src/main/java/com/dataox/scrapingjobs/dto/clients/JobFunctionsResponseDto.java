package com.dataox.scrapingjobs.dto.clients;

import lombok.Builder;

@Builder
public record JobFunctionsResponseDto (
        Long id,
        String name
){
}

package com.dataox.scrapingjobs.dto.clients;

import lombok.Builder;

@Builder
public record TagsResponseDto(
        String value,
        String highlighted,
        Integer count){
}

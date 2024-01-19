package com.dataox.scrapingjobs.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchRequestDto (
        List<Long> jobFunctionsId,
        OrderBy orderBy,
        SortBy sortBy
){
    public enum SortBy {
        ASC, DESC
    }

    public enum OrderBy {
        CREATED
    }
}

package com.dataox.scrapingjobs.specifications;

import com.dataox.scrapingjobs.dto.SearchRequestDto;
import com.dataox.scrapingjobs.entities.Job;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static com.dataox.scrapingjobs.dto.SearchRequestDto.*;

public class JobSpecification {
    public static Specification<Job> hasJobFunctionId(List<Long> jobFunctionId) {
        return (root, query, cb) -> {
            if (jobFunctionId != null) {
                Join<Object, Object> jobFunctionJoin = root.join("jobFunction");
                return jobFunctionJoin.get("id").in(jobFunctionId);
            } else {
                return cb.isTrue(cb.literal(true));
            }
        };
    }

    public static <T> Specification<T> sortBy(SortBy sortBy, OrderBy orderBy) {
        return (root, query, cb) -> {
            if (sortBy != null && orderBy != null) {
                if (sortBy == SortBy.ASC) {
                    query.orderBy(cb.asc(root.get(orderBy.toString().toLowerCase())));
                } else if (sortBy == SortBy.DESC) {
                    query.orderBy(cb.desc(root.get(orderBy.toString().toLowerCase())));
                }
            }
            return cb.isTrue(cb.literal(true));
        };
    }
}

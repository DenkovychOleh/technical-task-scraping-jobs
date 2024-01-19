package com.dataox.scrapingjobs.clients;

import com.dataox.scrapingjobs.dto.clients.ResultsResponseDto;
import com.dataox.scrapingjobs.dto.clients.ResultDto;
import com.dataox.scrapingjobs.dto.clients.TagsResponseDto;
import com.dataox.scrapingjobs.exceptions.InternalServerErrorException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Component
public class HttpCLiClient implements CLiClient {

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public List<TagsResponseDto> getTags() {
        String url = "https://jobs.techstars.com/api/search/industry_tags?networkId=89&type=Job";
        Request request = new Request.Builder().url(url).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            String jsonResponse = Objects.requireNonNull(response.body()).string();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonResponse, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultsResponseDto getJobs(List<String> jobFunctions) {
        String url2 = "https://jobs.techstars.com/api/search/jobs?networkId=89&hitsPerPage=20&page=0&filters=";
        String url = url2 + buildUrl(jobFunctions);
        Request request = new Request.Builder().url(url).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String jsonResponse = Objects.requireNonNull(response.body()).string();
            return mapJobsJsonResponse(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUrl(List<String> jobFunctions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%28");
        IntStream.range(0, jobFunctions.size()).forEach(i -> {
            stringBuilder.append("job_functions%3A%22");
            stringBuilder.append(jobFunctions.get(i).replace(" ", "+").replace("&", "%26"));
            stringBuilder.append("%22");
            if ((jobFunctions.size() > 1) && (i != jobFunctions.size() - 1)) {
                stringBuilder.append("+OR+");
            }
        });

        stringBuilder.append("%29");
        return stringBuilder.toString();
    }

    private ResultsResponseDto mapJobsJsonResponse(String jsonResponse) {
        List<ResultDto> resultDtos = new ArrayList<>();
        int nbHits;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode resultsNode = root.path("results");
            nbHits = root.findValue("nbHits").asInt();
            if (resultsNode.isArray() && !resultsNode.isEmpty()) {
                JsonNode hitsNode = resultsNode.get(0).path("hits");
                if (hitsNode.isArray() && !hitsNode.isEmpty()) {
                    for (JsonNode jsonNode : hitsNode) {
                        resultDtos.add(ResultDto.builder()
                                .positionName(jsonNode.path("title").asText())
                                .organizationUrl(jsonNode.path("url").asText())
                                .logoUrl(jsonNode.path("organization").path("logo_url").asText())
                                .organizationTitle(jsonNode.path("organization").path("name").asText())
                                .organizationTags(getOrganizationTags(jsonNode.path("organization").path("industry_tags")))
                                .laborFunction(getLaborFunction(jsonNode.path("_highlightResult").path("job_functions")))
                                .locations(getLocations(jsonNode.path("locations")))
                                .createdAt(mapLongToLocalDateTime(jsonNode.path("created_at").asLong()))
                                .build());
                    }
                } else {
                    log.info("No hits found in the response.");
                }
            } else {
                log.info("No results found in the response.");
            }

        } catch (Exception e) {
            throw new InternalServerErrorException("Internal server error occurred while processing the response.");
        }
        return ResultsResponseDto.builder()
                .nbJobs(nbHits)
                .results(resultDtos)
                .build();
    }


    private LocalDateTime mapLongToLocalDateTime(long createdAt) {
        Instant instant = Instant.ofEpochSecond(createdAt);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private String getOrganizationTags(JsonNode tagsNode) {
        List<String> tags = new ArrayList<>();
        if (tagsNode.isArray()) {
            for (JsonNode tag : tagsNode) {
                tags.add(tag.asText());
            }
        }
        return tags.toString();
    }

    private String getLaborFunction(JsonNode jobFunctionsNode) {
        if (jobFunctionsNode.isArray() && !jobFunctionsNode.isEmpty()) {
            return jobFunctionsNode.get(0).path("value").asText();
        }
        return "";
    }

    private String getLocations(JsonNode locationsNode) {
        List<String> locations = new ArrayList<>();
        if (locationsNode.isArray()) {
            for (JsonNode location : locationsNode) {
                locations.add(location.asText());
            }
        }
        return locations.toString();
    }
}

package com.example.springnotionapidemo.client;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class NotionClient {

    private final WebClient webClient;

    @Value("${notion.api.secret}")
    private String accessToken;

    @Value("${notion.api.version}")
    private String notionVersion;

    @Value("${notion.database.id}")
    private String databaseId;

    public String queryDataBase() {
        try {
            String uri = UriComponentsBuilder.fromHttpUrl("https://api.notion.com/v1/databases/" + databaseId + "/query")
                    .toUriString();


            return webClient.post()
                    .uri(uri)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .header("Notion-Version", notionVersion)
                    .retrieve()
                    .bodyToMono(String.class).block();

        } catch (Exception e) {
            return e.getMessage();
        }
    }

}

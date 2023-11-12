package com.example.springnotionapidemo.service;

import com.example.springnotionapidemo.dto.SampleDataBaseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class Parser {


    public String getResults(String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try {


            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode results = jsonNode.get("results");


            return  results.toPrettyString();

        } catch (JsonProcessingException e) {

            return e.getMessage();
        }
    }

    public String getFirstResult(String results){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNodes = objectMapper.readTree(results);
            return trimData(jsonNodes.get(0)).toPrettyString();
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    public List<String> getResultList(String results){
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            JsonNode jsonNodes = objectMapper.readTree(results);
            List<JsonNode> jsonNodeList = new ArrayList<>();
            for (JsonNode jsonNode : jsonNodes) {
                jsonNodeList.add(trimData(jsonNode));
            }
            return jsonNodeList.stream().map(JsonNode::toString).toList();
        } catch (JsonProcessingException e) {
            return e.getMessage().lines().toList();
        }


    }

    public JsonNode trimData(JsonNode result){
        return result.get("properties");
    }

    public SampleDataBaseDTO parse(String property) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(property);
        SampleDataBaseDTO sampleDataBaseDTO = new SampleDataBaseDTO();
        sampleDataBaseDTO.setName(jsonNode.path("name").path("title").path(0).path("text").path("content").asText());

        List<String> tagList = new ArrayList<>();
        JsonNode tags = jsonNode.path("tag").path("multi_select");
        for (JsonNode tag : tags) {
            tagList.add(tag.path("name").asText());
        }
        sampleDataBaseDTO.setTag(tagList);

        sampleDataBaseDTO.setUrl(jsonNode.path("URL").path("url").asText());
        sampleDataBaseDTO.setNumber(jsonNode.path("number").path("number").asLong());

        return sampleDataBaseDTO;
    }


}

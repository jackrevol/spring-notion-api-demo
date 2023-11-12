package com.example.springnotionapidemo.controller;

import com.example.springnotionapidemo.dto.SampleDataBaseDTO;
import com.example.springnotionapidemo.service.NotionService;
import com.example.springnotionapidemo.service.Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final NotionService notionService;

    private final Parser parser;

    @GetMapping("/test/row")
    public String testRow() {
        return notionService.getSome();
    }

    @GetMapping("/test/results")
    public String testResult() {
        String json = notionService.getSome();

        return parser.getResults(json);
    }


    @GetMapping("/test/results/list")
    public List<String> testResultList() {
        String json = notionService.getSome();
        String resultString = parser.getResults(json);

        return  parser.getResultList(resultString);
    }

    @GetMapping("/test/results/single")
    public String testResultSingle() {
        String json = notionService.getSome();
        String resultString = parser.getResults(json);

        return  parser.getFirstResult(resultString);
    }

    @GetMapping("/test/dto")
    public SampleDataBaseDTO getDTO() throws IOException {
        String json = notionService.getSome();
        String resultString = parser.getResults(json);
        String result = parser.getFirstResult(resultString);
        return  parser.parse(result);
    }

    @PatchMapping("/test")
    public String test2(@RequestBody String body){
        return  notionService.update(body);
    }



}

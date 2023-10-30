package com.example.springnotionapidemo.controller;

import com.example.springnotionapidemo.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final NotionService notionService;

    @GetMapping("/test")
    public String test() {
        return notionService.getSome();
    }


}

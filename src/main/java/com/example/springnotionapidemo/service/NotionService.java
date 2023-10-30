package com.example.springnotionapidemo.service;

import com.example.springnotionapidemo.client.NotionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotionService {

    private final NotionClient notionClient;

    public String getSome() {
        return notionClient.queryDataBase();
    }

}

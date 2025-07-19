package com.example.summarizer.controller;

import com.example.summaerizer.model.SummaryRequest;
import com.example.summaerizer.model.SummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import example.summarizer.service.OpenAiClientService;

@RestController
@RequestMapping("/summarize")
public class SummarizerController {
    @Autowired
    private OpenAiClientService openAiService;

    @PostMapping
    public SummaryResponse summarize (@RequestBody SummaryRequest summaryRequest){
        String summary = openAiService.generateSummary(summaryRequest.getText());
        return new SummaryResponse(summary);
    }

}
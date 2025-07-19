package com.project.summarizer.controller;

import com.project.summarizer.model.SummaryRequest;
import com.project.summarizer.model.SummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.summarizer.service.OpenAiClientService;

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
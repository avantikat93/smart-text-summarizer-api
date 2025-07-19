package com.project.summarizer.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiClientService {

    private final OpenAiService openAiService;

    @Autowired
    public OpenAiClientService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public OpenAiClientService(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public String generateSummary(String inputText) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Summarize the following text:\n" + inputText)
                .model("text-davinci-003")
                .maxTokens(100)
                .temperature(0.7)
                .build();

        CompletionResult result = openAiService.createCompletion(completionRequest);

        if (!result.getChoices().isEmpty()) {
            return result.getChoices().get(0).getText().trim();
        } else {
            return "No summary could be generated.";
        }
    }
}

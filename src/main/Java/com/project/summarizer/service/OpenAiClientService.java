package example.summarizer.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAiClientService {

    @Value("${openai.api.key}")
    private String apiKey;

    public String generateSummary(String inputText) {
        OpenAiService service = new OpenAiService(apiKey);

        // Build the completion request
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Summarize the following text:\n" + inputText)
                .model("gpt-3.5-turbo")
                .maxTokens(100)
                .temperature(0.7)
                .build();

        CompletionResult result = service.createCompletion(completionRequest);

        if (!result.getChoices().isEmpty()) {
            return result.getChoices().get(0).getText().trim();
        } else {
            return "No summary could be generated.";
        }
    }
}
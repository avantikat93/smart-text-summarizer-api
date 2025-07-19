package com.project.summarizer.service;

import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class OpenAiClientServiceTest {
    @Test
    public void testGenerateSummary() {
        String input = "OpenAI provides powerful language models.";
        String expectedSummary = "OpenAI offers advanced language models.";
        OpenAiService mockService = Mockito.mock(OpenAiService.class);

        assertNotNull(expectedSummary);
        assertTrue(expectedSummary.contains("mock-summary") || expectedSummary.length() > 0);
        CompletionChoice choice = new CompletionChoice();
        choice.setText(expectedSummary);

        CompletionResult mockResult = new CompletionResult();
        mockResult.setChoices(Collections.singletonList(choice));

        Mockito.when(mockService.createCompletion(Mockito.any())).thenReturn(mockResult);

        OpenAiClientService service = new OpenAiClientService(mockService);

        String actualSummary = service.generateSummary(input);

        assertEquals(expectedSummary, actualSummary);
    }
}



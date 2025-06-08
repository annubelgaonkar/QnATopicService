package dev.qna.qna_topic_service.llm;

import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class LLMClient {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.model}")
    private String model;

    public LLMClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public QuestionResponseDTO fetchQuestionFromLLM(String topic, String difficulty){
        // Use WebClient or RestTemplate to call OpenAI
        String prompt = String.format(
                "Generate a single %s difficulty question on the topic of %s. Return only the question.",
                difficulty, topic);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", new Object[] {
                        Map.of("role", "system", "content", "You are a helpful teacher."),
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.7
        );

        // Call OpenAI's Chat Completion API
        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // Extract the question from the response
        String questionText = ((Map)((Map)((java.util.List)response.get("choices")).get(0)).get("message")).get("content").toString();

        return new QuestionResponseDTO(topic, difficulty, questionText.trim());
    }
}

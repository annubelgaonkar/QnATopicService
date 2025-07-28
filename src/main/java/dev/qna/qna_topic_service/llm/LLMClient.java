package dev.qna.qna_topic_service.llm;

import dev.qna.qna_topic_service.dto.EvaluationResponseDTO;
import dev.qna.qna_topic_service.dto.GenerateQuestionRequestDTO;
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

    @Value("${llm.prompt.question}")
    private String questionPrompt;

    @Value("${llm.prompt.feedback}")
    private String feedbackPrompt;

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

        String prompt = String.format(questionPrompt, topic, difficulty);

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

    //logic for evaluating user's answer
    public EvaluationResponseDTO fetchFeedbackFromLLM(String topic, String question, String userAnswer){
        String prompt = String.format(feedbackPrompt, topic, question, userAnswer);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are a helpful evaluator."),
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.7
        );
        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String feedback = ((Map)((Map)((java.util.List)response.get("choices")).get(0)).get("message")).get("content").toString();

        return new EvaluationResponseDTO(feedback.trim());
    }

    public String fetchQuestionWithPrompt(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages",new Object[]{
                        Map.of("role", "system", "content", "You are a helpful tutor."),
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.7
        );
        Map response = webClient.post()
                .uri(apiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String question = ((Map)((Map)((java.util.List)response
                .get("choices")).get(0))
                .get("message")).get("content").toString();

        return question.trim();
    }
}

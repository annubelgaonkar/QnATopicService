package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import org.springframework.stereotype.Service;
import dev.qna.qna_topic_service.llm.LLMClient;

@Service
public class LLMQuestionServiceImpl implements QuestionService {

    private final LLMClient llmClient;
    public LLMQuestionServiceImpl(LLMClient llmClient) {
        this.llmClient = llmClient;
    }

    @Override
    public QuestionResponseDTO getQuestion(String topic, String difficulty) {
        return llmClient.fetchQuestionFromLLM(topic, difficulty);
    }
}

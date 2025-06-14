package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.qna.qna_topic_service.llm.LLMClient;

import java.util.concurrent.atomic.AtomicLong;

@Service
@AllArgsConstructor
public class LLMQuestionServiceImpl implements QuestionService {

    @Autowired
    private final LLMClient llmClient;

    @Override
    public QuestionResponseDTO getQuestion(String topic, String difficulty) {
        // Call the LLM to fetch the generated question
        QuestionResponseDTO questionResponseDTO = llmClient.fetchQuestionFromLLM(topic, difficulty);
       return questionResponseDTO;
    }
}

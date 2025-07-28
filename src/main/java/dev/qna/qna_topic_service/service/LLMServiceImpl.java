package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.dto.EvaluationRequestDTO;
import dev.qna.qna_topic_service.dto.EvaluationResponseDTO;
import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.qna.qna_topic_service.client.LLMClient;

@Service
@AllArgsConstructor
public class LLMServiceImpl implements LLMService {

    @Autowired
    private final LLMClient llmClient;

    @Override
    public QuestionResponseDTO generateQuestion(String topic, String difficulty) {
        // Call the LLM to fetch the generated question
        QuestionResponseDTO questionResponseDTO = llmClient.fetchQuestionFromLLM(topic, difficulty);
       return questionResponseDTO;
    }

    @Override
    public EvaluationResponseDTO evaluateAnswer(EvaluationRequestDTO evaluationRequestDTO) {
        return llmClient.fetchFeedbackFromLLM(evaluationRequestDTO.getTopic(),
                evaluationRequestDTO.getQuestion(),
                evaluationRequestDTO.getUserAnswer());
    }

}

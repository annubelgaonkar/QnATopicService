package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.dto.EvaluationRequestDTO;
import dev.qna.qna_topic_service.dto.EvaluationResponseDTO;
import dev.qna.qna_topic_service.dto.QuestionResponseDTO;

public interface LLMService {
    QuestionResponseDTO generateQuestion(String topic, String difficulty);
    EvaluationResponseDTO evaluateAnswer(EvaluationRequestDTO evaluationRequestDTO);
}

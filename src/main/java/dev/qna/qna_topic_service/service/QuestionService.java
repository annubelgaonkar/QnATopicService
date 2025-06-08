package dev.qna.qna_topic_service.service;

import dev.qna.qna_topic_service.dto.QuestionResponseDTO;

public interface QuestionService {
    QuestionResponseDTO getQuestion(String topic, String difficulty);
}

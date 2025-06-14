package dev.qna.qna_topic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {
    private String topic;
    private String difficulty;
    private String questionText;

}

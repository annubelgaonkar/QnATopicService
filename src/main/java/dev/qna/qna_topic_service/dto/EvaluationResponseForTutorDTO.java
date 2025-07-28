package dev.qna.qna_topic_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationResponseForTutorDTO {
    private String feedback;
    private String nextQuestion;
}

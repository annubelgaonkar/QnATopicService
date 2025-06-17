package dev.qna.qna_topic_service.controller;

import dev.qna.qna_topic_service.dto.*;
import dev.qna.qna_topic_service.llm.LLMClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/llm")
@AllArgsConstructor
public class LLMController {

    private final LLMClient llmClient;

    //Generate a question
    @PostMapping("/generate")
    public ResponseEntity<BaseResponseDTO<QuestionResponseDTO>> generateQuestion(
            @RequestBody QuestionRequestDTO request) {
        QuestionResponseDTO responseDTO = llmClient.fetchQuestionFromLLM(request.getTopic(), request.getDifficulty());
        return ResponseEntity.ok(new BaseResponseDTO<>(
                true,
                "Question generated successfully",
                responseDTO));
    }

    //Evaluate user's answer
    @PostMapping("/evaluate")
    public ResponseEntity<BaseResponseDTO<EvaluationResponseDTO>> evaluateAnswer(
            @RequestBody EvaluationRequestDTO request){
            EvaluationResponseDTO responseDTO = llmClient.fetchFeedbackFromLLM(
                    request.getTopic(),request.getQuestion(), request.getUserAnswer()
            );
            return ResponseEntity.ok(new BaseResponseDTO<>(
                    true,
                    "Evaluation completed",
                    responseDTO
            ));
    }

}

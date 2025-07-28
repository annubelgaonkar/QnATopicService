package dev.qna.qna_topic_service.controller;

import dev.qna.qna_topic_service.dto.*;
import dev.qna.qna_topic_service.llm.LLMClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/llm")
@AllArgsConstructor
@Slf4j
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

    // New endpoint for tutorSession that doesn't require difficulty
    @PostMapping("/generateTutor")
    public ResponseEntity<BaseResponseDTO<GenerateQuestionResponseDTO>> generateQuestionForTutor(
            @RequestBody GenerateQuestionRequestDTO request) {

        log.info("Received topic: {}", request.getTopic());

        if (request.getTopic() == null || request.getTopic().isBlank()) {
            throw new IllegalArgumentException("Topic cannot be null or blank");
        }

        String prompt = String.format(
                "Generate a specific question for the topic '%s'. " +
                        "Avoid asking 'What is %s?'. The question should test understanding of concepts.",
                request.getTopic(), request.getTopic());

        String question = llmClient.fetchQuestionWithPrompt(prompt);

        return ResponseEntity.ok(new BaseResponseDTO<>(
                true,
                "Generated",
                new GenerateQuestionResponseDTO(question)));
    }

}

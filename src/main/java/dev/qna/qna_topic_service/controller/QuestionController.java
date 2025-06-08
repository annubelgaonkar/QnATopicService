package dev.qna.qna_topic_service.controller;

import dev.qna.qna_topic_service.dto.QuestionRequestDTO;
import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import dev.qna.qna_topic_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/generate")
    public ResponseEntity<QuestionResponseDTO> generateQuestion(
            @RequestBody QuestionRequestDTO request) {
        return ResponseEntity.ok(
                questionService.getQuestion(request.getTopic(), request.getDifficulty())
        );
    }

}

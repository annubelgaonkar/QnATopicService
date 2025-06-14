package dev.qna.qna_topic_service.controller;

import dev.qna.qna_topic_service.dto.BaseResponseDTO;
import dev.qna.qna_topic_service.dto.QuestionRequestDTO;
import dev.qna.qna_topic_service.dto.QuestionResponseDTO;
import dev.qna.qna_topic_service.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private QuestionService questionService;

    @PostMapping("/generate")
    public ResponseEntity<BaseResponseDTO<QuestionResponseDTO>> generateQuestion(
            @RequestBody QuestionRequestDTO request) {
        QuestionResponseDTO responseDTO = questionService.getQuestion(request.getTopic(), request.getDifficulty());
        BaseResponseDTO<QuestionResponseDTO> baseResponseDTO = new BaseResponseDTO<>(
                true,
                "Question generated successfully",
                responseDTO
        );
        return new ResponseEntity<>(baseResponseDTO, HttpStatus.OK);

    }

}

package com.quiz.controller;

import com.quiz.model.Question;
import com.quiz.model.dto.QuestionDTO;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/createAll")
    @Operation(summary = "Create Questions ")
    public ApiResponse<List<Question>> createAllQuestion(@RequestBody List<QuestionDTO> questionDTOs) {
        return ApiResponse.<List<Question>>builder().operationResultData(questionService.saveAll(questionDTOs)).build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All Questions ")
    public ApiResponse<List<Question>> getAllQuestion() {
        return ApiResponse.<List<Question> >builder()
                .operationResultData(questionService.getAll()).build();
    }
}

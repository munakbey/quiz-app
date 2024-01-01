package com.quiz.controller;

import com.quiz.model.Quiz;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    @Operation(summary = "Create Quiz ")
    public ApiResponse<List<Quiz>> createQuiz(@RequestBody List<Quiz> quizs) {
        return ApiResponse.<List<Quiz>>builder().operationResultData(quizService.saveAll(quizs)).build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All Quiz")
    public ApiResponse<List<Quiz>> getAllQuiz() {
        List<Quiz> quizs = quizService.getAll();

        return ApiResponse.<List<Quiz>>builder()
                .operationResultData(quizs)
                .build();
    }

}

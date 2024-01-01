package com.quiz.controller;

import com.quiz.model.QuizQuestion;
import com.quiz.model.dto.QuizQuestionDTO;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.QuizQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizQuestion")
public class QuiziQuestionController {

    private final QuizQuestionService quizQuestionService;

    @PostMapping("/create")
    @Operation(summary = "Create QuizQuestion ")
    public ApiResponse<QuizQuestion> createQuiz(@RequestBody QuizQuestionDTO quizQuestionDTO) {
        return ApiResponse.<QuizQuestion>builder().operationResultData(quizQuestionService.save(quizQuestionDTO)).build();
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All QuizQuestion")
    public ApiResponse<List<QuizQuestion>> getAllQuiz() {
        List<QuizQuestion> quizQuestions = quizQuestionService.getAll();

        return ApiResponse.<List<QuizQuestion>>builder()
                .operationResultData(quizQuestions)
                .build();
    }
}

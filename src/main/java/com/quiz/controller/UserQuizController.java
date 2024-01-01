package com.quiz.controller;

import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserQuizDTO;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.UserQuizService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userQuiz")
public class UserQuizController {

    private final UserQuizService userQuizService;

    @PostMapping("/create")
    @Operation(summary = "Create User Quiz")
    public ApiResponse<Void> createUserQuiz(@RequestBody List<UserQuizDTO> userQuizDTOS) {
        userQuizService.assignQuizToUsers(userQuizDTOS);
        return ApiResponse.<Void >builder().build();
    }

    @GetMapping("/all")
    public ApiResponse<List<UserQuiz>> getAllUsers() {
        return ApiResponse.<List<UserQuiz>>builder().operationResultData(userQuizService.getAll()).build();
    }

    @GetMapping("/isComplated/{userId}/{quizId}")
    public ApiResponse<Boolean> isCompletedQuiz(@PathVariable Long userId, @PathVariable Long quizId) {
        return ApiResponse.<Boolean>builder().operationResultData(userQuizService.isComplated(userId, quizId)).build();
    }
}

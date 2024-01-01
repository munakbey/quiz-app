package com.quiz.controller;

import com.quiz.model.UserAnswer;
import com.quiz.model.dto.UserAnswerDTO;
import com.quiz.model.response.ApiResponse;
import com.quiz.model.response.ScoreResponse;
import com.quiz.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userAnswer")
public class UserAnswerController {

    private final UserAnswerService userAnswerService;

    @PostMapping("/create")
    public ApiResponse<UserAnswer> create(@RequestBody UserAnswerDTO userAnswerDTO) {
        UserAnswer savedUserAnswer = userAnswerService.save(userAnswerDTO);
        return ApiResponse.<UserAnswer>builder().operationResultData(savedUserAnswer).build();
    }

    @GetMapping("/complete/{userQuizId}")
    public ApiResponse<Void> completeUserQuiz(@PathVariable Long userQuizId) {
        userAnswerService.complateUserQuiz(userQuizId);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/answered/{userQuizId}")
    public ApiResponse<List<UserAnswer>> getAnsweredQuestions(@PathVariable Long userQuizId) {
        return ApiResponse.<List<UserAnswer>>builder()
                .operationResultData(userAnswerService.getAnsweredQuestions(userQuizId)).build();
    }

    @GetMapping("/score/{userQuizId}")
    public ApiResponse<ScoreResponse> getUsersScore(@PathVariable Long userQuizId) {
        return ApiResponse.<ScoreResponse>builder()
                .operationResultData(userAnswerService.getScore(userQuizId)).build();
    }
}

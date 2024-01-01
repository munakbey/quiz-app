package com.quiz.controller;

import com.quiz.model.Option;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.OptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/option")
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/create")
    @Operation(summary = "Create Question Option")
    public ApiResponse<List<Option>> createOptions(@RequestBody List<Option> options) {
        List<Option> savedOptions = optionService.saveAll(options);
        return ApiResponse.<List<Option>>builder().operationResultData(savedOptions).build();
    }

    @GetMapping("/all")
    public ApiResponse<List<Option>> getAllOptions() {
        List<Option> options = optionService.getAll();
        return ApiResponse.<List<Option>>builder().operationResultData(options).build();
    }
}

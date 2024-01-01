package com.quiz.controller;

import com.quiz.model.User;
import com.quiz.model.response.ApiResponse;
import com.quiz.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create User")
    public ApiResponse<User> createUser(@RequestBody User user) {
        return ApiResponse.<User>builder().operationResultData(userService.save(user)).build();
    }

    @GetMapping("/all")
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.<List<User>>builder().operationResultData(userService.getAll()).build();
    }
}

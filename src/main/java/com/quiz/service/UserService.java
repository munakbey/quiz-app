package com.quiz.service;

import com.quiz.model.User;

import java.util.List;


public interface UserService extends BaseService<User,User>{
    List<User> getAll();
}

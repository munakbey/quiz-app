package com.quiz.service.serviceImpl;

import com.quiz.model.User;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}

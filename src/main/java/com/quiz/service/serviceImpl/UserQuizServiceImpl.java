package com.quiz.service.serviceImpl;

import com.quiz.model.Quiz;
import com.quiz.model.User;
import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserQuizDTO;
import com.quiz.repository.UserQuizRepository;
import com.quiz.service.QuizService;
import com.quiz.service.UserQuizService;
import com.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQuizServiceImpl implements UserQuizService {

    private final UserQuizRepository repository;
    private final UserService userService;
    private final QuizService quizService;

    @Override
    public UserQuiz save(UserQuizDTO entity) {
     return null;
    }

    @Override
    public Optional<UserQuiz> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void assignQuizToUsers(List<UserQuizDTO> userQuizDTOList) {
        for (UserQuizDTO userQuizDTO : userQuizDTOList) {
            User user = userService.findById(userQuizDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("AppUser not found with id: " + userQuizDTO.userId()));

            Quiz quiz = quizService.findById(userQuizDTO.quizId())
                    .orElseThrow(() -> new IllegalArgumentException("AppQuiz not found with id: " + userQuizDTO.quizId()));

            UserQuiz userQuiz = new UserQuiz();
            userQuiz.setUser(user);
            userQuiz.setQuiz(quiz);
            userQuiz.setAssignedDate(Calendar.getInstance().getTime());

            repository.save(userQuiz);
        }
    }

    @Override
    public void save(UserQuiz userQuiz) {
        repository.save(userQuiz);
    }

    @Override
    public List<UserQuiz> getAll() {
        return repository.findAll();
    }

    @Override
    public Boolean isComplated(Long userId, Long quizId) {
        UserQuiz userQuiz = Optional.ofNullable(repository.findByUserIdAndQuizId(userId, quizId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "UserQuiz not found with userId: " + userId + " and quizId: " + quizId));

        return Objects.nonNull(userQuiz.getCompletedDate());
    }

}

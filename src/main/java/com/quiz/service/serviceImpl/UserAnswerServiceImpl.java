package com.quiz.service.serviceImpl;

import com.quiz.model.Question;
import com.quiz.model.UserAnswer;
import com.quiz.model.UserQuiz;
import com.quiz.model.dto.UserAnswerDTO;
import com.quiz.model.response.ScoreResponse;
import com.quiz.repository.UserAnswerRepository;
import com.quiz.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAnswerServiceImpl implements UserAnswerService {

    private final UserAnswerRepository repository;
    private final UserQuizService userQuizService;
    private final QuestionService questionService;
    private final OptionService optionService;

    @Override
    public UserAnswer save(UserAnswerDTO entity) {
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserQuiz(userQuizService.findById(entity.userQuizId()).orElseThrow());
        Question question= questionService.findById(entity.questionId()).orElseThrow();
        userAnswer.setQuestion(question);

        if(Objects.nonNull(entity.selectedOptionId())){
            userAnswer.setSelectedOption(optionService.findById(entity.selectedOptionId()).orElse(null));
            userAnswer.setIsCorrect(question.getCorrectOption().getId().equals(entity.selectedOptionId()));
        }

        return repository.save(userAnswer);
    }

    @Override
    public Optional<UserAnswer> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void complateUserQuiz(Long userQuizId) {
      List<UserAnswer> userAnswers =repository.findByUserQuizId(userQuizId) ;
        UserQuiz userQuiz = userAnswers.get(0).getUserQuiz();
        userQuiz.setCompletedDate(Calendar.getInstance().getTime());
        userQuiz.setTrueAnswerCount((int) userAnswers.stream().filter(UserAnswer::getIsCorrect).count());
        userQuiz.setWrongAnswerCount((int) userAnswers.stream()
                .filter(userAnswer -> Boolean.FALSE.equals(userAnswer.getIsCorrect())).count());

        userQuizService.save(userQuiz);
    }

    @Override
    public List<UserAnswer> getAnsweredQuestions(Long userQuizId) {
        return repository.findByUserQuizIdAndSelectedOptionIsNotNull(userQuizId);
    }

    @Override
    public ScoreResponse getScore(Long userQuizId) {
        List<UserAnswer> userAnswers = repository.findByUserQuizId(userQuizId);

        int correctCount = (int) userAnswers.stream()
                .filter(userAnswer -> Boolean.TRUE.equals(userAnswer.getIsCorrect()))
                .count();

        int incorrectCount = (int) userAnswers.stream()
                .filter(userAnswer -> Boolean.FALSE.equals(userAnswer.getIsCorrect()))
                .count();

        int notAnsweredCount = (int) userAnswers.stream()
                .filter(userAnswer -> Objects.isNull(userAnswer.getIsCorrect()))
                .count();

        return new ScoreResponse(correctCount, incorrectCount, notAnsweredCount);
    }
}

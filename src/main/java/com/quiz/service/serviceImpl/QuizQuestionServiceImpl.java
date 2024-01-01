package com.quiz.service.serviceImpl;

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.model.QuizQuestion;
import com.quiz.model.dto.QuizQuestionDTO;
import com.quiz.repository.QuizQuestionRepository;
import com.quiz.service.QuestionService;
import com.quiz.service.QuizQuestionService;
import com.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final QuizQuestionRepository repository;
    private final QuizService quizService;
    private final QuestionService questionService;

    @Override
    public QuizQuestion save(QuizQuestionDTO entity) {
        Quiz quiz = quizService.findById(entity.quizId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found with id: " + entity.quizId()));

        List<Question> questions = entity.questionIds().stream()
                .map(questionId -> questionService.findById(questionId)
                        .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + questionId)))
                .collect(Collectors.toList());

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuiz(quiz);
        quizQuestion.setQuestions(questions);
        return repository.save(quizQuestion);
    }

    @Override
    public Optional<QuizQuestion> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<QuizQuestion> getAll() {
        return repository.findAll();
    }
}

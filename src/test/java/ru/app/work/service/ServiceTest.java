package ru.app.work.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.app.work.dao.QuestionDao;
import ru.app.work.domain.Answer;
import ru.app.work.domain.Question;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @InjectMocks
    private TestServiceImpl testServiceImpl;

    @Mock
    private IOService ioService;

    @Mock
    private QuestionDao dao;


    @DisplayName("Should format questions and answers correctly")
    @Test
    void shouldFormatQuestionsWithAnswersCorrectly() {
        var answerOne = new Answer("John", true);
        var answerTwo = new Answer("Max", false);
        var answers = List.of(answerOne, answerTwo);
        var question = new Question("Who are you?", answers);
        when(dao.findAll()).thenReturn(Collections.singletonList(question));
        testServiceImpl.executeTest();
    }
}
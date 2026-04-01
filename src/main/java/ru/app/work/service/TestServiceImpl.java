package ru.app.work.service;

import ru.app.work.dao.QuestionDao;
import ru.app.work.domain.Answer;
import ru.app.work.domain.Question;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    public TestServiceImpl(IOService ioService, QuestionDao questionDao) {
        this.ioService = ioService;
        this.questionDao = questionDao;
    }

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        questionDao.findAll().stream()
                .flatMap(this::formatQuestionWithAnswer)
                .forEach(System.out::println);
    }

    private Stream<String> formatQuestionWithAnswer(Question question) {
        var lines = new ArrayList<String>();
        lines.add(String.format("%s: %s", Question.class.getSimpleName(), question.text()));
        IntStream.range(0, question.answers().size())
                .mapToObj(j -> String.format("%s (%d): %s%n isCorrect=%s",
                        Answer.class.getSimpleName(),
                        j + 1,
                        question.answers().get(j).text(),
                        question.answers().get(j).isCorrect()))
                .forEach(lines::add);

        lines.add("");

        return lines.stream();
    }
}

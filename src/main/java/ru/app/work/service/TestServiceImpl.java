package ru.app.work.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.app.work.dao.QuestionDao;
import ru.app.work.domain.Student;
import ru.app.work.domain.TestResult;

import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;


    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question : questions) {
            ioService.printLine(question.text());
            var answers = question.answers();
            IntStream.range(0, answers.size())
                    .forEach(i -> ioService.printFormattedLine("%d. %s", i + 1, answers.get(i).text()));

            int answerNumber = ioService.readIntForRangeWithPrompt(1, answers.size(),
                    "Your answer (enter number):",
                    "Invalid input. Please enter a number between 1 and " + answers.size());

            var isAnswerValid = answers.get(answerNumber - 1).isCorrect();
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }
}

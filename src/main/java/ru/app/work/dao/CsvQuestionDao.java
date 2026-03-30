package ru.app.work.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.app.work.config.TestFileNameProvider;
import ru.app.work.dao.dto.QuestionDto;
import ru.app.work.domain.Question;
import ru.app.work.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        String fileName = fileNameProvider.getTestFileName();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (isNull(inputStream)) throw new QuestionReadException(String.format("File not found: %s", fileName));

            var questions = new CsvToBeanBuilder<QuestionDto>(new InputStreamReader(inputStream))
                    .withType(QuestionDto.class)
                    .withSeparator(';')
                    .withSkipLines(1)
                    .build()
                    .parse();

            return questions.stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();

        } catch (IOException e) {
            throw new QuestionReadException(String.format("Error reading questions from file: %s", fileName), e);
        }

    }
}

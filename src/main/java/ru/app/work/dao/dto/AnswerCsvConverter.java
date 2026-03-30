package ru.app.work.dao.dto;

import com.opencsv.bean.AbstractCsvConverter;
import ru.app.work.domain.Answer;

public class AnswerCsvConverter extends AbstractCsvConverter {
    @Override
    public Object convertToRead(String value) {
        var valueArr = value.split("%");
        return new Answer(valueArr[0], Boolean.parseBoolean(valueArr[1]));
    }
}

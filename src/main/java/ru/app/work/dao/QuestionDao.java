package ru.app.work.dao;

import ru.app.work.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}

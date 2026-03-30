package ru.app.work.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}

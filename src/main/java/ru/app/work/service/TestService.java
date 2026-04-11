package ru.app.work.service;

import ru.app.work.domain.Student;
import ru.app.work.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}

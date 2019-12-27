package ru.otus.homework;

import org.junit.jupiter.api.Test;
import ru.otus.homework.TestCases.BeforeAllError_AfterAll;
import ru.otus.homework.TestCases.BeforeAll_BeforeEachError_AfterEach_AfterAll;
import ru.otus.homework.TestCases.BeforeAll_BeforeEach_Test_AfterEach_AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TestRunnerTest {

    @Test
    void BeforeAllError_AfterAll() {
        assertThrows(
                Exception.class,
                () -> {
                    TestRunner.run(BeforeAllError_AfterAll.class);
                }
        );
    }

    @Test
    void BeforeAll_BeforeEach_Test_AfterEach_AfterAll() throws Exception {
        TestRunner.run(BeforeAll_BeforeEach_Test_AfterEach_AfterAll.class);
    }

    @Test
    void BeforeAll_BeforeEachError_AfterEach_AfterAll() {
        assertThrows(
                Exception.class,
                () -> {
                    TestRunner.run(BeforeAll_BeforeEachError_AfterEach_AfterAll.class);
                }
        );
    }
}
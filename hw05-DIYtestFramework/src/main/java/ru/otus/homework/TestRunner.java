package ru.otus.homework;

public class TestRunner {
    public static void run(Class<?> testClass) throws Exception {
        new TestService().executeTestSequence(testClass);
    }
}

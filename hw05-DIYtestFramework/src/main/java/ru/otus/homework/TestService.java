package ru.otus.homework;

import ru.otus.homework.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestService {

    private Map<Class<? extends Annotation>, List<Method>> testSequenceMap = new HashMap<>();

    {
        testSequenceMap.put(BeforeAll.class, new ArrayList<>());
        testSequenceMap.put(BeforeEach.class, new ArrayList<>());
        testSequenceMap.put(Test.class, new ArrayList<>());
        testSequenceMap.put(AfterEach.class, new ArrayList<>());
        testSequenceMap.put(AfterAll.class, new ArrayList<>());
    }

    public void executeTestSequence(Class<?> testClass) throws Exception {
        createTestSequence(testClass);
        try {
            for (Method beforeAllMethod : testSequenceMap.get(BeforeAll.class)) {
                invokeUnacceptable(beforeAllMethod, null);
            }
            for (Method testMethod : testSequenceMap.get(Test.class)) {
                Constructor<?> constructor = testClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object testClassInstance = constructor.newInstance();
                constructor.setAccessible(false);
                try {
                    for (Method beforeEachMethod : testSequenceMap.get(BeforeEach.class)) {
                        invokeUnacceptable(beforeEachMethod, testClassInstance);
                    }
                    invokeUnacceptable(testMethod, testClassInstance);
                } finally {
                    for (Method afterEachMethod : testSequenceMap.get(AfterEach.class)) {
                        invokeUnacceptable(afterEachMethod, testClassInstance);
                    }
                }
            }
        } finally {
            for (Method afterAllMethod : testSequenceMap.get(AfterAll.class)) {
                invokeUnacceptable(afterAllMethod, null);
            }
        }
    }

    private void invokeUnacceptable(Method beforeEachMethod, Object obj) throws InvocationTargetException, IllegalAccessException {
        beforeEachMethod.setAccessible(true);
        beforeEachMethod.invoke(obj);
        beforeEachMethod.setAccessible(false);
    }

    private void createTestSequence(Class<?> testClass) {
        Arrays.stream(
                testClass.getDeclaredMethods()
        ).forEach(
                method -> testSequenceMap.keySet().stream()
                        .filter(annotation -> method.getAnnotation(annotation) != null)
                        .findFirst()
                        .map(annotation -> testSequenceMap.get(annotation).add(method))
        );

    }
}

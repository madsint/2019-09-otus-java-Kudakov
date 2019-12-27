package ru.otus.homework.TestCases;

import ru.otus.homework.annotations.*;

public class BeforeAllError_AfterAll {
    BeforeAllError_AfterAll() {
        System.out.println("Call of the constructor");
    }

    @BeforeAll
    static void beforeAll() throws Exception {
        System.out.println("BeforeAll");
        throw new Exception();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach3() throws Exception {
        System.out.println("BeforeEach3");
        throw new Exception();
    }

    @BeforeEach
    void beforeEach() throws Exception {
        System.out.println("BeforeEach");
        throw new Exception();
    }

    @BeforeEach
    void beforeEach2() throws Exception {
        System.out.println("BeforeEach2");
        throw new Exception();
    }

    @Test
    void testOne() throws Exception {
        System.out.println("testOne");
        throw new Exception();
    }

    @Test
    void testTwo() throws Exception {
        System.out.println("testTwo");
        throw new Exception();
    }

    @AfterEach
    void afterEach() throws Exception {
        System.out.println("AfterEach");
        throw new Exception();
    }

    @AfterEach
    void afterEach1() throws Exception {
        System.out.println("AfterEach1");
        throw new Exception();
    }

    @AfterEach
    void afterEach2() throws Exception {
        System.out.println("AfterEach2");
        throw new Exception();
    }
}

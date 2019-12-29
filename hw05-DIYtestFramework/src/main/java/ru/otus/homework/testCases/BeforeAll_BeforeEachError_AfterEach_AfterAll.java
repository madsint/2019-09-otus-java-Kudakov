package ru.otus.homework.testCases;

import ru.otus.homework.annotations.*;

public class BeforeAll_BeforeEachError_AfterEach_AfterAll {
    BeforeAll_BeforeEachError_AfterEach_AfterAll() {
        System.out.println("Call of the constructor");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @AfterAll
    public static void afterAll() {
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
    void testOne() {
        System.out.println("testOne");
    }

    @Test
    void testTwo() {
        System.out.println("testTwo");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }

    @AfterEach
    void afterEach1() {
        System.out.println("AfterEach1");
    }

    @AfterEach
    void afterEach2() {
        System.out.println("AfterEach2");
    }
}

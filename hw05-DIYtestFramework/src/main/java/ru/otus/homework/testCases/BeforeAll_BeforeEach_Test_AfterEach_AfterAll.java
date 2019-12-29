package ru.otus.homework.testCases;

import ru.otus.homework.annotations.*;

public class BeforeAll_BeforeEach_Test_AfterEach_AfterAll {
    BeforeAll_BeforeEach_Test_AfterEach_AfterAll() {
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
    void beforeEach3() {
        System.out.println("BeforeEach3");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @BeforeEach
    void beforeEach2() {
        System.out.println("BeforeEach2");
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

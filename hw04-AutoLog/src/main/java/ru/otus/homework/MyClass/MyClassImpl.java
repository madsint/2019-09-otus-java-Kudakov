package ru.otus.homework.MyClass;

public class MyClassImpl implements MyClassInterface {
    @Log
    public void methodForLog(int a, int b) {
        System.out.println("method 1 for @Log");
    }

    @Log
    public void methodForLog(String c) {
        System.out.println("method 2 for @Log");
    }

    @Log
    public void methodForLog(int b) {
        System.out.println("method NOT for @Log");
    }

    @Log
    public void method(int a) {
        System.out.println("method");
    }
}

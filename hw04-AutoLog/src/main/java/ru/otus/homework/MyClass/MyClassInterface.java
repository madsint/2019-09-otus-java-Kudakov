package ru.otus.homework.MyClass;

import ru.otus.homework.annotations.Log;

public interface MyClassInterface {

    void methodForLog(int a, int b);

 
    void methodForLog(String c);

    /**
     * This method is NOT for @Log
     */
    void methodForLog(int b);

    void method(int a);
}

package ru.otus.homework;

import ru.otus.homework.MyClass.MyClassImpl;
import ru.otus.homework.MyClass.MyClassInterface;
import ru.otus.homework.MyHandler.MyHandler;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        MyClassInterface myClass = (MyClassInterface) Proxy.newProxyInstance(
                        Main.class.getClassLoader(),
                        new Class<?>[]{MyClassInterface.class},
                        new MyHandler(new MyClassImpl())
        );

        myClass.method(1);
        myClass.methodForLog(2,3);
        myClass.methodForLog(4);
        myClass.methodForLog("5");
    }
}

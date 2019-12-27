package ru.otus.homework.MyHandler;

import ru.otus.homework.MyClass.MyClassInterface;
import ru.otus.homework.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyHandler implements InvocationHandler {
    private final MyClassInterface myClass;
    private Set<Method> methodsForLog = new HashSet<>();

    public MyHandler(MyClassInterface myClass) {
        this.myClass = myClass;
        for (Method myClassInterfaceMethod : MyClassInterface.class.getMethods()) {
            if(myClassInterfaceMethod.getAnnotation(Log.class)!=null){
                methodsForLog.add(myClassInterfaceMethod);
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodsForLog.contains(method)) {
            System.out.println(
                    "executed method: " +
                            method.getName() +
                            ", params: " +
                            Arrays.toString(args)
                                    .replaceAll("\\[", "")
                                    .replaceAll("\\]", "")
            );
        }
        return method.invoke(myClass, args);
    }
}

package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {

    private TestUtils() {
    }

    public static void mapInit(Map<AnnotationsType, List<String>> methodsMap, Class<?> clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method method : declaredMethods) {
            String methodName = method.getName();

            var hasAnnotationBefore = method.isAnnotationPresent(Before.class);
            if (hasAnnotationBefore) {
                methodsMap.get(AnnotationsType.BEFORE).add(methodName);
            }

            var hasAnnotationTest = method.isAnnotationPresent(Test.class);
            if (hasAnnotationTest) {
                methodsMap.get(AnnotationsType.TEST).add(methodName);
            }

            var hasAnnotationAfter = method.isAnnotationPresent(After.class);
            if (hasAnnotationAfter) {
                methodsMap.get(AnnotationsType.AFTER).add(methodName);
            }
        }
    }

    public static Map<AnnotationsType, List<String>> getEmptyMethodsMap() {

        var resultMap = new HashMap<AnnotationsType, List<String>>();

        resultMap.put(AnnotationsType.BEFORE, new ArrayList<>());
        resultMap.put(AnnotationsType.TEST, new ArrayList<>());
        resultMap.put(AnnotationsType.AFTER, new ArrayList<>());

        return resultMap;
    }

    public static Object invokeMethod(Object object, String name) {
        try {
            var method = object.getClass().getMethod(name);
            return method.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

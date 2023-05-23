package ru.otus.test;

import java.util.List;
import java.util.Map;

public class TestFramework {

    public void start(String className) {

        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<AnnotationsType, List<String>> methodsMap = TestUtils.getEmptyMethodsMap();
        TestUtils.mapInit(methodsMap, clazz);

        print("Start test");
        int successTests = 0;
        int failedTests = 0;
        int countTest = 0;

        for (String testMethodName : methodsMap.get(AnnotationsType.TEST)) {
            countTest++;
            print("Start test " + testMethodName);
            try {
                startTestInstance(clazz, testMethodName, methodsMap);
                print(testMethodName + " success!");
                successTests++;
            } catch (RuntimeException e) {
                print(testMethodName + " failed!");
                failedTests++;
            }
        }
        print("Statistics:");
        print("Test count: " + countTest);
        print("Success: " + successTests);
        print("Failed: " + failedTests);
    }

    private void startTestInstance(Class<?> clazz, String testMethodName, Map<AnnotationsType, List<String>> methodsMap) {
        Object classObject;
        boolean noErrorOccurred = true;

        try {
            classObject = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String methodName : methodsMap.get(AnnotationsType.BEFORE)) {
            try {
                TestUtils.invokeMethod(classObject, methodName);
            } catch (RuntimeException e) {
                noErrorOccurred = false;
            }
        }

        if (noErrorOccurred) {
            try {
                TestUtils.invokeMethod(classObject, testMethodName);
            } catch (RuntimeException e) {
                noErrorOccurred = false;
            }
        }

        for (String methodName : methodsMap.get(AnnotationsType.AFTER)) {
            TestUtils.invokeMethod(classObject, methodName);
        }

        if (!noErrorOccurred) {
            throw new RuntimeException();
        }
    }

    private void print(String message) {
        System.out.println(message);
    }
}

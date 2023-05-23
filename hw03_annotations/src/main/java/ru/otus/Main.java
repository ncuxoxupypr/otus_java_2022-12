package ru.otus;

import ru.otus.test.TestFramework;

public class Main {
    public static void main(String[] args) {
        var testApplication = new TestFramework();
        testApplication.start("ru.otus.test.TestClass");
    }
}
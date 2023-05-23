package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;


public class TestClass {

    @Before
    private void setUp() {
        System.out.println("Before method");
    }

    @Before
    private void setUpTwo() {
        System.out.println("Before two method");
    }

    @Test
    private void testMethodOne() {
        System.out.println("Test method one");
        throw new RuntimeException("Test failed");
    }

    @Test
    private void testMethodTwo() {
        System.out.println("Test method two");
    }

    @After
    private void tearDown() {
        System.out.println("After method");
    }
}

package ru.otus;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {

        List<Integer> toSort = Arrays.asList(15, 57, 1, 111, 96, 588);
        toSort.sort(Ordering.natural().reverse());
        System.out.println(toSort);
    }
}
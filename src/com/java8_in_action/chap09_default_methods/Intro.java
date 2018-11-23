package com.java8_in_action.chap09_default_methods;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by sofia on 12/23/16.
 */
public class Intro {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }

}

package com.java8_in_action.chap05_working_with_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Laziness {

    public static void main(String... args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> twoEvenSquares = numbers.stream()
                .filter(n -> {
                    System.out.println("filtering "+n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("mapping "+n);
                    return n * n;
                })
                .limit(2)
                .collect(Collectors.toList());

        twoEvenSquares.forEach(System.out::println);
    }

}

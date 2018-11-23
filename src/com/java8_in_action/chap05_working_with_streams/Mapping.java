package com.java8_in_action.chap05_working_with_streams;

import com.java8_in_action.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Mapping {

    public static void main(String... args) {
        // map
        List<String> dishNames = Dish.MENU.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(dishNames);
        System.out.println();

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(wordLengths);
        System.out.println();

        // flatMap
        words.stream()
                .flatMap((String line) -> Arrays.stream(line.split(" ")))
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);

        List<int[]> pairs = numbers1.stream()
                .flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[]{i,j}))
                .filter(pair -> (pair[0]+pair[1]) % 3 == 0)
                .collect(Collectors.toList());

        pairs.forEach(pair -> System.out.println("("+pair[0]+","+pair[1]+")"));
        System.out.println();
    }

}

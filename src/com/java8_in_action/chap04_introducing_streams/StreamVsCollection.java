package com.java8_in_action.chap04_introducing_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sofia on 12/22/16.
 */
public class StreamVsCollection {

    public static void main(String... args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
    }

}

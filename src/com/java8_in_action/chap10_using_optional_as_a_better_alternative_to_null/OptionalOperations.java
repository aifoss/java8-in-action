package com.java8_in_action.chap10_using_optional_as_a_better_alternative_to_null;

import java.util.Optional;

/**
 * Created by sofia on 12/23/16.
 */
public class OptionalOperations {

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j) {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }


    public static void main(String... args) {
        System.out.println(max(Optional.of(3), Optional.of(5)));
        System.out.println(max(Optional.empty(), Optional.of(5)));
    }

}

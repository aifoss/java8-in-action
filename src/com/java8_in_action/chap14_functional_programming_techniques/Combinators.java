package com.java8_in_action.chap14_functional_programming_techniques;

import java.util.function.Function;

/**
 * Created by sofia on 12/23/16.
 */
public class Combinators {

    public static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n-1, f));
    }

    private static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }


    public static void main(String... args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));
    }

}

package com.java8_in_action.chap13_thinking_functionally;

import java.util.stream.LongStream;

/**
 * Created by sofia on 12/23/16.
 */
public class Recursion {

    public static long factorialIterative(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    public static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n-1);
    }

    public static long factorialTailRecursive(long n) {
        return factorialHelper(1, n);
    }

    private static long factorialHelper(long acc, long n) {
        return n == 1 ? acc : factorialHelper(acc * n, n-1);
    }

    public static long factorialStream(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long a, long b) -> a * b);
    }


    public static void main(String[] args) {
        System.out.println(factorialIterative(5));
        System.out.println(factorialRecursive(5));
        System.out.println(factorialTailRecursive(5));
        System.out.println(factorialStream(5));
    }

}

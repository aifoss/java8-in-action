package com.java8_in_action.e_app_b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;

/**
 * Created by sofia on 12/25/16.
 */
public class AppendixB {

    public static void main(String... args) {
        Map<String, Integer> map = new HashMap<>();

        Integer count = map.getOrDefault("Aston Martin", 0);
        System.out.println(count);

        Function<String, Integer> f = s -> 1;
        count = map.computeIfAbsent("Aston Martin", f);
        System.out.println(count);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x -> x * 2);
        System.out.println(numbers);

        LongAdder adder = new LongAdder();
        adder.add(10);
        long sum = adder.sum();
        System.out.println(sum);

        LongAccumulator acc = new LongAccumulator(Long::sum, 0);
        acc.accumulate(10);
        sum = acc.get();
        System.out.println(sum);

        ConcurrentHashMap<String, Integer> map2 = new ConcurrentHashMap<>();

        map2.putIfAbsent("one", 1);
        map2.putIfAbsent("two", 2);
        map2.putIfAbsent("three", 3);

        Optional<Integer> maxValue = Optional.of(map2.reduceValues(1, Integer::max));
        System.out.println(maxValue.get());

        long size = map2.mappingCount();
        System.out.println(size);

        System.out.println(map2.keySet());

        int[] array = { 5, 4, 3, 2, 1};
        Arrays.parallelSort(array);
        System.out.println(Arrays.toString(array));

        int[] evenNumbers = new int[10];
        Arrays.setAll(evenNumbers, i -> i * 2);
        System.out.println(Arrays.toString(evenNumbers));

        int[] ones = new int[10];
        Arrays.fill(ones, 1);
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
        System.out.println(Arrays.toString(ones));
        Arrays.parallelPrefix(ones, (a, b) -> a + b);
        System.out.println(Arrays.toString(ones));

        String authors = String.join(" ", "Paul", "Mario", "Alan");
        System.out.println(authors);
    }

}

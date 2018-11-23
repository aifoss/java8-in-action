package com.java8_in_action.chap05_working_with_streams;

import org.java8.java8_in_action.common.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Filtering {

    public static void main(String... args) {
        // filtering with predicate

        List<Dish> vegetarianMenu = Dish.MENU.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        vegetarianMenu.forEach(System.out::println);
        System.out.println();

        // filtering unique elements

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        // truncating stream

        List<Dish> dishesLimit3 = Dish.MENU.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        dishesLimit3.forEach(System.out::println);
        System.out.println();

        // skipping elements

        List<Dish> dishesSkip2 = Dish.MENU.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        dishesSkip2.forEach(System.out::println);
        System.out.println();
    }

}

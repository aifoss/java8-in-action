package com.java8_in_action.chap04_introducing_streams;

import com.java8_in_action.common.Dish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class StreamBasic {

    public static List<String> getLowCaloricDishNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCalDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCalDishes.add(dish);
            }
        }

        List<String> lowCalDishNames = new ArrayList<>();
        Collections.sort(lowCalDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish dish : lowCalDishes) {
            lowCalDishNames.add(dish.getName());
        }

        return lowCalDishNames;
    }

    public static List<String> getLowCaloricDishNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        getLowCaloricDishNamesInJava7(Dish.MENU).forEach(System.out::println);
        System.out.println("---");
        getLowCaloricDishNamesInJava8(Dish.MENU).forEach(System.out::println);
    }

}

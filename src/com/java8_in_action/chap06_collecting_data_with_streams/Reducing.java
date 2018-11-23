package com.java8_in_action.chap06_collecting_data_with_streams;

import com.java8_in_action.common.Dish;

import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Reducing {

    public static int calculateTotalCalories() {
        return Dish.MENU.stream().collect(
                Collectors.reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j)
        );
    }

    public static int calculateTotalCaloriesWithMethodReference() {
        return Dish.MENU.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
    }

    public static int calculateTotalCaloriesWithoutCollectors() {
        return Dish.MENU.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    public static int calculateTotalCaloriesUsingSum() {
        return Dish.MENU.stream().mapToInt(Dish::getCalories).sum();
    }


    public static void main(String ... args) {
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
        System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

}

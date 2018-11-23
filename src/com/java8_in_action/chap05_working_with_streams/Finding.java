package com.java8_in_action.chap05_working_with_streams;

import org.java8.java8_in_action.common.Dish;

import java.util.Optional;

/**
 * Created by sofia on 12/22/16.
 */
public class Finding {

    public static boolean isVegetarianFriendlyMenu() {
        return Dish.MENU.stream().anyMatch(Dish::isVegetarian);
    }

    public static boolean isHealthyMenu() {
        return Dish.MENU.stream().allMatch(d -> d.getCalories() < 1000);
    }

    public static boolean isHealthyMenu2() {
        return Dish.MENU.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    public static Optional<Dish> findVegetarianDish() {
        return Dish.MENU.stream().filter(Dish::isVegetarian).findAny();
    }


    public static void main(String... args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

}

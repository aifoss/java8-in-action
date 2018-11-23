package com.java8_in_action.chap06_collecting_data_with_streams;

import com.java8_in_action.common.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Summarizing {

    public static long howManyDishes() {
        return Dish.MENU.stream().collect(Collectors.counting());
    }

    public static Dish findMostCaloricDish() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                .get();
    }

    public static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCalorieComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCalorieComparator);
        return Dish.MENU.stream().collect(Collectors.reducing(moreCaloricOf)).get();
    }

    public static int calculateTotalCalories() {
        return Dish.MENU.stream().collect(Collectors.summingInt(Dish::getCalories));
    }

    public static Double calculateAverageCalories() {
        return Dish.MENU.stream().collect(Collectors.averagingInt(Dish::getCalories));
    }

    public static IntSummaryStatistics calculateMenuStatistics() {
        return Dish.MENU.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    }

    public static String getShortMenu() {
        return Dish.MENU.stream().map(Dish::getName).collect(Collectors.joining());
    }

    public static String getShortMenuCommaSeparated() {
        return Dish.MENU.stream().map(Dish::getName).collect(Collectors.joining(", "));
    }


    public static void main(String ... args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }

}

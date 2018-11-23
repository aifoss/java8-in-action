package com.java8_in_action.chap06_collecting_data_with_streams;

import com.java8_in_action.common.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };


    public static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType));
    }

    public static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                );
    }

    public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByTypeAndCaloricLevel() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy((Dish dish) -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                        }))
                );
    }

    public static Map<Dish.Type, Long> countDishesInGroups() {
        return Dish.MENU.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    }

    public static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
                );
    }

    public static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                        Optional::get
                                ))
                );
    }

    public static Map<Dish.Type, Integer> sumCaloriesByType() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.summingInt(Dish::getCalories))
                );
    }

    public static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.mapping(
                                        dish -> {
                                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                            else return CaloricLevel.FAT;
                                        },
                                        Collectors.toSet()
                                ))
                );
    }


    public static void main(String ... args) {
        System.out.println("Dishes grouped by type:\n" + groupDishesByType() + "\n");
        System.out.println("Dishes grouped by caloric level:\n" + groupDishesByCaloricLevel() + "\n");
        System.out.println("Dishes grouped by type and caloric level:\n" + groupDishesByTypeAndCaloricLevel() + "\n");
        System.out.println("Count dishes in groups:\n" + countDishesInGroups() + "\n");
        System.out.println("Most caloric dishes by type:\n" + mostCaloricDishesByType() + "\n");
        System.out.println("Most caloric dishes by type:\n" + mostCaloricDishesByTypeWithoutOptionals() + "\n");
        System.out.println("Sum calories by type:\n" + sumCaloriesByType() + "\n");
        System.out.println("Caloric levels by type:\n" + caloricLevelsByType() + "\n");
    }

}

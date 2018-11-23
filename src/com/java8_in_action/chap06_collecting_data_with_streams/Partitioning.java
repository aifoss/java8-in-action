package com.java8_in_action.chap06_collecting_data_with_streams;

import com.java8_in_action.common.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/22/16.
 */
public class Partitioning {

    public static Map<Boolean, List<Dish>> partitionByVegetarian() {
        return Dish.MENU.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
    }

    public static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return Dish.MENU.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
    }

    public static Object mostCaloricPartitionedByVegetarian() {
        return Dish.MENU.stream()
                .collect(
                        Collectors.partitioningBy(Dish::isVegetarian,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
    }


    public static void main(String ... args) {
        System.out.println("Dishes partitioned by vegetarian:\n" + partitionByVegetarian() + "\n");
        System.out.println("Vegetarian Dishes by type:\n" + vegetarianDishesByType() + "\n");
        System.out.println("Most caloric dishes by vegetarian:\n" + mostCaloricPartitionedByVegetarian() + "\n");
    }

}

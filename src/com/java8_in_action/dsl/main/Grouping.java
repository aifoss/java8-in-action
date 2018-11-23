package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.common.Dish;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/25/16.
 */
public class Grouping {

    public enum CaloricLevel { DIET, NORMAL, FAT }


    public static class GroupingBuilder<T, D, K> {
        private final Collector<? super T, ?, Map<K, D>> collector;

        public GroupingBuilder(Collector<? super T, ?, Map<K, D>> collector) {
            this.collector = collector;
        }

        public Collector<? super T, ?, Map<K, D>> get() {
            return collector;
        }

        public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
            return new GroupingBuilder(Collectors.groupingBy(classifier));
        }

        public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier) {
            return new GroupingBuilder(Collectors.groupingBy(classifier, collector));
        }
    }


    public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByTypeAndCaloricLevel2() {
        return Dish.MENU.stream().collect(
                twoLevelGroupingBy(Dish::getType, dish -> getCaloricLevel(dish))
        );
    }

    public static <A, B, T> Collector<T, ?, Map<A, Map<B, List<T>>>> twoLevelGroupingBy(
            Function<? super T, ? extends A> f1,
            Function<? super T, ? extends B> f2) {
        return Collectors.groupingBy(f1, Collectors.groupingBy(f2));
    }

    public static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishesByTypeAndCaloricLevel3() {
        Collector<? super Dish, ?, Map<Dish.Type, Map<CaloricLevel, List<Dish>>>> c = GroupingBuilder.groupOn(
                (Dish dish) -> getCaloricLevel(dish))
                .after(Dish::getType)
                .get();
        return Dish.MENU.stream().collect(c);
    }


    private static CaloricLevel getCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }


    public static void main(String ... args) {
        System.out.println("Dishes grouped by type and caloric level: " + groupDishesByTypeAndCaloricLevel2());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishesByTypeAndCaloricLevel3());
    }

}

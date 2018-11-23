package com.java8_in_action.e_app_c;

import org.java8.java8_in_action.common.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sofia on 12/25/16.
 */
public class StreamForkerExample {

    public static void processMenu() {
        Stream<Dish> menuStream = Dish.MENU.stream();

        StreamForker.Results results = new StreamForker<>(menuStream)
                .fork("shortMenu", s -> s.map(Dish::getName).collect(Collectors.joining(", ")))
                .fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
                .fork("mostCaloricDish", s -> s.collect(
                        Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
                    ).get())
                .fork("dishesByType", s -> s.collect(Collectors.groupingBy(Dish::getType)))
                .getResults();

        String shortMenu = results.get("shortMenu");
        int totalCalories = results.get("totalCalories");
        Dish mostCaloricDish = results.get("mostCaloricDish");
        Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");

        System.out.println("Short menu: " + shortMenu);
        System.out.println("Total calories: " + totalCalories);
        System.out.println("Most caloric dish: " + mostCaloricDish);
        System.out.println("Dishes by type: " + dishesByType);
    }


    public static void main(String... args) throws Exception {
        processMenu();
    }

}

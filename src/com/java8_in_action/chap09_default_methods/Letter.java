package com.java8_in_action.chap09_default_methods;

import java.util.function.Function;

/**
 * Created by sofia on 12/23/16.
 */
public class Letter {

    public static String addHeader(String text) {
        return "From Peter, Mario, and Alan: "+text;
    }

    public static String addFooter(String text) {
        return text+" Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("C\\+\\+", "**Censored**");
    }


    public static void main(String... args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> pipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        System.out.println(pipeline.apply("C++ stay away from me!"));
    }

}

package com.java8_in_action.e_app_a;

import java.util.Arrays;

/**
 * Created by sofia on 12/25/16.
 */
@Author(name = "Paul")
@Author(name = "Mario")
@Author(name = "Alan")
public class Book {

    public static void main(String... args) {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).stream().forEach(a -> System.out.println(a.name()));
    }

}

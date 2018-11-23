package com.java8_in_action.chap09_default_methods;

/**
 * Created by sofia on 12/23/16.
 */
public class Diamond {

    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B extends A {}

    interface C extends A {}

    static class D implements B, C {}


    public static void main(String... args) {
        new D().hello();
    }

}

package com.java8_in_action.chap09_default_methods;

/**
 * Created by sofia on 12/23/16.
 */
public class Ambiguous {

    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements A, B {
        public void hello() {
            A.super.hello();
        }
    }


    public static void main(String... args) {
        new C().hello();
    }

}

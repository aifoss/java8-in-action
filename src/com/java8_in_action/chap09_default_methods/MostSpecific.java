package com.java8_in_action.chap09_default_methods;

/**
 * Created by sofia on 12/23/16.
 */
public class MostSpecific {

    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B extends A {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements A, B {}

    static class D implements A {}

    static class E extends D implements A, B {}

    static class F implements A, B {
        public void hello() {
            System.out.println("Hello from F");
        }
    }

    static class G extends F implements A, B {}


    public static void main(String... args) {
        new C().hello();
        new E().hello();
        new G().hello();
    }

}

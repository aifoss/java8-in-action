package com.java8_in_action.chap14_functional_programming_techniques;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by sofia on 12/23/16.
 */
public class LazyLists {

    interface MyList<T> {
        T head();
        MyList<T> tail();
        MyList<T> filter(Predicate<T> p);

        default boolean isEmpty() {
            return true;
        }
    }

    static class MyLinkedList<T> implements MyList<T> {
        final T head;
        final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ?
                    this :
                    p.test(head()) ?
                            new MyLinkedList<>(head(), tail()).filter(p) :
                            tail().filter(p);
        }

        public boolean isEmpty() {
            return false;
        }
    }

    static class Empty<T> implements MyList<T> {
        public T head() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail.get();
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ?
                    this :
                    p.test(head()) ?
                            new LazyList<>(head(), () -> tail().filter(p)) :
                            tail().filter(p);
        }

        public boolean isEmpty() {
            return false;
        }
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n+1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
                () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        printAll(numbers.tail());
    }


    public static void main(String... args) {
        MyList<Integer> list = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));

        System.out.println(list.head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two+" "+three+" "+four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_five = primes(numbers).tail().tail().head();
        System.out.println(prime_two+" "+prime_three+" "+prime_five);
    }

}

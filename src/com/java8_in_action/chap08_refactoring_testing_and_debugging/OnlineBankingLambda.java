package com.java8_in_action.chap08_refactoring_testing_and_debugging;

import java.util.function.Consumer;

/**
 * Created by sofia on 12/23/16.
 */
public class OnlineBankingLambda {

    private static class Customer {}

    private static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }


    public static void main(String... args) {
        new OnlineBankingLambda().processCustomer(337, (Customer c) -> System.out.println("Hello!"));;
    }

}

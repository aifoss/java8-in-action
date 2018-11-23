package com.java8_in_action.chap08_refactoring_testing_and_debugging;

/**
 * Created by sofia on 12/23/16.
 */
public abstract class OnlineBanking {

    private static class Customer {}

    private static class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }

    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);

}

package com.java8_in_action.dsl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 12/25/16.
 */
public class Order {

    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getValue).sum();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void print() {
        System.out.println("ORDER: ");
        System.out.println("Customer: "+customer);
        System.out.println("Trades:");
        trades.stream().forEach(System.out::println);
        System.out.println();
    }

}

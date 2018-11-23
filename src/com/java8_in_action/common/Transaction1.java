package com.java8_in_action.common;

/**
 * Created by sofia on 12/22/16.
 */
public class Transaction1 {

    private Trader trader;
    private int year;
    private int value;

    public Transaction1(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value: " + this.value + "}";
    }

}

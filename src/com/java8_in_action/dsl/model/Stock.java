package com.java8_in_action.dsl.model;

/**
 * Created by sofia on 12/25/16.
 */
public class Stock {

    private String symbol;
    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Override
    public String toString() {
        return "[STOCK: Symbol: "+symbol+", Market: "+market+"]";
    }

}

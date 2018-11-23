package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.dsl.model.Order;
import org.java8.java8_in_action.dsl.model.Stock;
import org.java8.java8_in_action.dsl.model.Trade;

import java.util.stream.Stream;

/**
 * Created by sofia on 12/25/16.
 */
public class OrderBuilderUsingNestedFunction {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(stock, price, quantity, Trade.Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(stock, price, quantity, Trade.Type.SELL);
    }

    private static Trade buildTrade(Stock stock, double price, int quantity, Trade.Type type) {
        Trade trade = new Trade();
        trade.setType(type);
        trade.setStock(stock);
        trade.setPrice(price);
        trade.setQuantity(quantity);
        return trade;
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market) {
        return market;
    }

}

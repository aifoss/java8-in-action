package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.dsl.model.Order;
import org.java8.java8_in_action.dsl.model.Stock;
import org.java8.java8_in_action.dsl.model.Trade;

import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingMethodChaining.forCustomer;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.*;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.at;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.buy;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.on;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.order;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.sell;
import static org.java8.java8_in_action.dsl.main.OrderBuilderUsingNestedFunction.stock;

/**
 * Created by sofia on 12/25/16.
 */
public class OrderBuilderClient {

    public static Order buildOrder_plain() {
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);

        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);

        order.addTrade(trade2);

        return order;
    }

    public static Order buildOrder_methodChaining() {
        return forCustomer("BigBank")
                .buy(80).stock("IBM").on("NYSE").at(125.00)
                .sell(50).stock("GOOGLE").on("NASDAQ").at(375.00)
                .end();
    }

    public static Order buildOrder_nestedFunction() {
        return order("BigBank",
                buy(80,
                        stock("IBM", on("NYSE")),
                        at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")),
                        at(375.00))
        );
    }

    public static Order buildOrder_lambda() {
        return OrderBuilderUsingLambda.order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(375.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });
    }


    public static void main(String... args) {
        Order order;

        order = buildOrder_plain();
        order.print();

        order = buildOrder_methodChaining();
        order.print();

        order = buildOrder_nestedFunction();
        order.print();

        order = buildOrder_lambda();
        order.print();
    }

}

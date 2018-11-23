package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.dsl.model.Order;
import org.java8.java8_in_action.dsl.model.Stock;
import org.java8.java8_in_action.dsl.model.Trade;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by sofia on 12/25/16.
 */
public class OrderBuilderUsingMixed {

    public static class TradeBuilder {
        private Trade trade = new Trade();

        public TradeBuilder quantity(int quantity) {
            trade.setQuantity(quantity);
            return this;
        }

        public TradeBuilder at(double price) {
            trade.setPrice(price);
            return this;
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(this, trade, symbol);
        }
    }

    public static class StockBuilder {
        private final TradeBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            this.stock.setSymbol(symbol);
        }

        public TradeBuilder on(String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return builder;
        }
    }

    public static Order forCustomer(String customer, TradeBuilder... builders) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(b -> order.addTrade(b.trade));
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.BUY);
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
        return buildTrade(consumer, Trade.Type.SELL);
    }

    private static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Trade.Type type) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);
        consumer.accept(builder);
        return builder;
    }


    public static void main(String... args) {
        Order order = forCustomer("BigBank",
                buy(t -> t.quantity(80)
                            .stock("IBM")
                            .on("NYSE")
                            .at(125.00)),
                sell(t -> t.quantity(50)
                            .stock("GOOGLE")
                            .on("NASDAQ")
                            .at(375.00)));

        order.print();
    }

}

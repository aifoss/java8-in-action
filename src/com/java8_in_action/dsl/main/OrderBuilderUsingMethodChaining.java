package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.dsl.model.Order;
import org.java8.java8_in_action.dsl.model.Stock;
import org.java8.java8_in_action.dsl.model.Trade;

/**
 * Created by sofia on 12/25/16.
 */
public class OrderBuilderUsingMethodChaining {

    public static class TradeBuilder {
        private final OrderBuilderUsingMethodChaining builder;
        private final Trade trade = new Trade();

        private TradeBuilder(OrderBuilderUsingMethodChaining builder, Trade.Type type, int quantity) {
            this.builder = builder;
            trade.setType(type);
            trade.setQuantity(quantity);
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(builder, trade, symbol);
        }
    }

    public static class TraderBuilderWithStock {
        private final OrderBuilderUsingMethodChaining builder;
        private final Trade trade;

        public TraderBuilderWithStock(OrderBuilderUsingMethodChaining builder, Trade trade) {
            this.builder = builder;
            this.trade = trade;
        }

        public OrderBuilderUsingMethodChaining at(double price) {
            trade.setPrice(price);
            return builder.addTrade(trade);
        }
    }

    public static class StockBuilder {
        private final OrderBuilderUsingMethodChaining builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(OrderBuilderUsingMethodChaining builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TraderBuilderWithStock on(String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return new TraderBuilderWithStock(builder, trade);
        }
    }


    public final Order order = new Order();

    private OrderBuilderUsingMethodChaining(String customer) {
        order.setCustomer(customer);
    }

    public static OrderBuilderUsingMethodChaining forCustomer(String customer) {
        return new OrderBuilderUsingMethodChaining(customer);
    }

    public OrderBuilderUsingMethodChaining addTrade(Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder(this, Trade.Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder(this, Trade.Type.SELL, quantity);
    }

    public Order end() {
        return order;
    }

}

package com.java8_in_action.dsl.main;

import org.java8.java8_in_action.dsl.model.Order;
import org.java8.java8_in_action.dsl.model.Tax;
import org.java8.java8_in_action.dsl.model.Trade;

import java.util.function.Function;

/**
 * Created by sofia on 12/25/16.
 */
public class TaxCalculator {

    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public static double calculate(Order order, boolean useRegional, boolean useGeneral, boolean useSurcharge) {
        double value = order.getValue();
        if (useRegional) value = Tax.regional(value);
        if (useGeneral) value = Tax.general(value);
        if (useSurcharge) value = Tax.surcharge(value);
        return value;
    }

    public TaxCalculator withTaxRegional() {
        useRegional = true;
        return this;
    }

    public TaxCalculator withTaxGeneral() {
        useGeneral = true;
        return this;
    }

    public TaxCalculator withTaxSurcharge() {
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order) {
        return calculate(order, useRegional, useGeneral, useSurcharge);
    }

    public Function<Double, Double> taxFunction = Function.identity();

    public TaxCalculator with(Function<Double, Double> f) {
        taxFunction.andThen(f);
        return this;
    }

    public double calculateF(Order order) {
        return taxFunction.apply(order.getValue());
    }


    public static void main(String... args) {
        Order order = new Order();
        Trade trade = new Trade();
        trade.setPrice(100.0);
        trade.setQuantity(10);
        order.addTrade(trade);

        double value;

        value = TaxCalculator.calculate(order, true, false, true);

        System.out.println(value);

        value = new TaxCalculator().withTaxRegional()
                .withTaxSurcharge()
                .calculate(order);

        System.out.println(value);

        value = new TaxCalculator().with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order);

        System.out.println(value);
    }

}

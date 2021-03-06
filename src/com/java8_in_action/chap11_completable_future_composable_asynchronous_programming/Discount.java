package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

import static com.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.delay;
import static com.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.format;

/**
 * Created by sofia on 12/23/16.
 */
public class Discount {

    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return format(price * (100 - code.percentage) / 100);
    }

}

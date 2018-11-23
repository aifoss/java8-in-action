package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

import java.util.Random;

import static org.java8.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.delay;
import static org.java8.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.format;

/**
 * Created by sofia on 12/23/16.
 */
public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0)*name.charAt(1)*name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name+":"+price+":"+code;
    }

    public double calculatePrice(String product) {
        delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }

}

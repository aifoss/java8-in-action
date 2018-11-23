package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static org.java8.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.delay;
import static org.java8.java8_in_action.chap11_completable_future_composable_asynchronous_programming.Utils.format;

/**
 * Created by sofia on 12/23/16.
 */
public class AsyncShop {

    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        random = new Random(name.charAt(0)*name.charAt(1)*name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        /*
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();

        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();

        return futurePrice;
        */

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public double calculatePrice(String product) {
        delay();
        if (true) throw new RuntimeException("product not available");
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }

}

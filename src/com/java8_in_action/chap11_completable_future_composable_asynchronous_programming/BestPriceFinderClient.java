package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by sofia on 12/23/16.
 */
public class BestPriceFinderClient {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();


    public static void main(String... args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
        bestPriceFinder.printPricesStream("myPhone27S");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime()-start) / 1_000_000;
        System.out.println(msg+" done in "+duration+" msecs");
    }

}

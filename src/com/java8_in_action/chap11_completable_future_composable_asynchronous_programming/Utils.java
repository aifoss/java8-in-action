package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by sofia on 12/23/16.
 */
public class Utils {

    private static final Random RANDOM = new Random(0);
    private static final DecimalFormat FORMATTER = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        int delay = 1000;
        // delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number) {
        synchronized (FORMATTER) {
            return new Double(FORMATTER.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        /*
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream()
                    .map(future -> future.join())
                    .collect(Collectors.<T>toList()));
        */

        return CompletableFuture.supplyAsync(
                () -> futures.stream()
                        .map(future -> future.join())
                        .collect(Collectors.<T>toList()));
    }

}

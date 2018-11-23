package com.java8_in_action.chap11_completable_future_composable_asynchronous_programming;

/**
 * Created by sofia on 12/23/16.
 */
public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387), GBP(1.69715), CAD(.92106), MXN(.07683);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }


    public static double getRate(Money src, Money dst) {
        return getRateWithDelay(src, dst);
    }

    private static double getRateWithDelay(Money src, Money dst) {
        Utils.delay();
        return dst.rate / src.rate;
    }

}

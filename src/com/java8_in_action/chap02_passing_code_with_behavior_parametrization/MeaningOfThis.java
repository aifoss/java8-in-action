package com.java8_in_action.chap02_passing_code_with_behavior_parametrization;

/**
 * Created by sofia on 12/22/16.
 */
public class MeaningOfThis {

    public final int value = 4;

    public void doIt() {
        int value = 6;

        Runnable r = new Runnable() {
            public final int value = 5;

            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };

        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }

}

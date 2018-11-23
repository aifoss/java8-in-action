package com.java8_in_action.chap14_functional_programming_techniques;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by sofia on 12/23/16.
 */
public class Currying {

    public static double converter(double x, double y, double z) {
        return x * y + z;
    }

    public static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    public static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }


    public static void main(String... args) {
        DoubleUnaryOperator convertCToF = curriedConverter(9.0/5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmToMi = curriedConverter(0.6214, 0);

        System.out.println(convertCToF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmToMi.applyAsDouble(20));

        DoubleUnaryOperator convertFToC = expandedCurriedConverter(-32, 5.0 / 9, 0);
        System.out.println(convertFToC.applyAsDouble(98.6));
    }

}

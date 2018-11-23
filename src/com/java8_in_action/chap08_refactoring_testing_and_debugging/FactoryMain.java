package com.java8_in_action.chap08_refactoring_testing_and_debugging;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by sofia on 12/23/16.
 */
public class FactoryMain {

    private static final Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }


    private interface Product {}
    private static class Loan implements Product {}
    private static class Stock implements Product {}
    private static class Bond implements Product {}

    private static class ProductFactory {
        public static Product createProduct(String name) {
            switch(name) {
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product: "+name);
            }
        }

        public static Product createProductLambda(String name) {
            Supplier<Product> p = map.get(name);
            if (p != null) return p.get();
            throw new RuntimeException("No such product: "+name);
        }
    }


    public static void main(String... args) {
        Product p1 = ProductFactory.createProduct("loan");

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();

        Product p3 = ProductFactory.createProductLambda("loan");
    }

}

package com.java8_in_action.chap08_refactoring_testing_and_debugging;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Created by sofia on 12/23/16.
 */
public class ChainOfResponsibilityMain {

    private abstract static class ProcessingObject<T> {
        protected ProcessingObject<T> successor;

        public T handle(T input) {
            T result = handleWork(input);
            if (successor != null) {
                return successor.handle(result);
            }
            return result;
        }

        abstract protected T handleWork(T input);

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }
    }

    private static class HeaderTextProcessing extends ProcessingObject<String> {
        @Override
        public String handleWork(String text) {
            return "From Peter, Mario, and Alan: "+text;
        }
    }

    private static class SpellCheckerProcessing extends ProcessingObject<String> {
        @Override
        public String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }


    public static void main(String... args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);

        String result1 = p1.handle("Aren't labdas really sexy?!");
        System.out.println(result1);

        UnaryOperator<String> headerProcessing = (String text) -> "From Peter, Mario, and Alan: "+text;
        UnaryOperator<String> spellCheckProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckProcessing);

        String result2 = pipeline.apply("Aren't labdas really sexy?!");
        System.out.println(result2);
    }

}

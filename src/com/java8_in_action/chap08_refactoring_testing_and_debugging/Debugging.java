package com.java8_in_action.chap08_refactoring_testing_and_debugging;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sofia on 12/23/16.
 */
public class Debugging {

    private static class Point {
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }


    public static void main(String... args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(p -> p.getX()).forEach(System.out::println);
    }

}

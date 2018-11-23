package com.java8_in_action.chap09_default_methods;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sofia on 12/23/16.
 */
public class Game {

    public static void main(String... args) {
        List<Resizable> resizableShapes = Arrays.asList(
                new Square(),
                new Triangle(),
                new Ellipse()
        );
        Utils.paint(resizableShapes);
    }

}

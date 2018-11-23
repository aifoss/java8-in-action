package com.java8_in_action.chap09_default_methods;

import java.util.List;

/**
 * Created by sofia on 12/23/16.
 */
public class Utils {

    public static void paint(List<Resizable> list) {
        list.forEach(r -> { r.setAbsoluteSize(42, 42); });
        list.forEach(r -> { r.setRelativeSize(2, 2); });
    }

}

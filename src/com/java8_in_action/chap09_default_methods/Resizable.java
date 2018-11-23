package com.java8_in_action.chap09_default_methods;

/**
 * Created by sofia on 12/23/16.
 */
public interface Resizable extends Drawable {

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);
    void setRelativeSize(int widthFactor, int heightFactor);

}

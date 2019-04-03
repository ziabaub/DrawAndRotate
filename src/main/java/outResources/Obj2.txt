package com;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.Serializable;

public class RectangleObject extends ObjectClass implements Serializable {





    public  RectangleObject() {
    }

    public RectangleObject(int valueHeight, int valueWidth) {
        super(valueHeight, valueWidth);
        randomColour();
    }


    @Override
    public Rectangle createObject(int valueHeight, int valueWidth) {

        this.valueHeight = valueHeight;
        this.valueWidth = valueWidth;
        randomColour();
        return Drawable();
    }

    @Override
    public Rectangle createObject() {

        return Drawable();
    }

    @Override
    public Rectangle Drawable() {
        Rectangle rectangle = new Rectangle(valueHeight, valueWidth);
        rectangle.setFill(getPaint());
        rectangle.setStroke(Color.DARKGRAY);
        rectangle.setStrokeWidth(2);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setLayoutX(layoutX);
        rectangle.setLayoutY(layoutY);
        return rectangle;

    }


//    public RectangleObject(int valueHeight, int valueWidth) {
//        super();
//        this.valueHeight = valueHeight;
//        this.valueWidth = valueWidth;
//        randomColour();
//    }


}

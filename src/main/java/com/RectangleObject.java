package com;

import com.ObjectClass;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

class RectangleObject extends ObjectClass implements Serializable {

    public int valueHeight;
    public int valueWidth;
    public int layoutX;
    public int layoutY;


    public Rectangle rectangleObject(int valueHeight, int valueWidth) {

        this.valueHeight = valueHeight;
        this.valueWidth = valueWidth;
        randomColour();
        return getDrawwable();
    }

    public Rectangle rectangleObject() {

        return getDrawwable();
    }

    private Rectangle getDrawwable() {
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

    public RectangleObject() {
    }

    public RectangleObject(int valueHeight, int valueWidth) {
        this.valueHeight = valueHeight;
        this.valueWidth = valueWidth;
        randomColour();
    }


    public int getValueHeight() {
        return valueHeight;
    }

    public void setValueHeight(int valueHeight) {
        this.valueHeight = valueHeight;
    }

    public int getValueWidth() {
        return valueWidth;
    }

    public void setValueWidth(int valueWidth) {
        this.valueWidth = valueWidth;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

}

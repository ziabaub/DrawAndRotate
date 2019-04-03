package com;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.Random;

public class ObjectClass implements Serializable {
    public int red;
    public int green;
    public int blue;
    int valueHeight;
    int valueWidth;
    int radius;
    int layoutX;
    int layoutY;


    public ObjectClass(int radius) {
        this.radius = radius;
    }

    public ObjectClass() {
    }

    public ObjectClass(int valueHeight, int valueWidth) {

    }

    public Shape createObject(int radius) {
        return null;
    }

    public Shape createObject() {

        return null;
    }

    public Shape createObject(int valueHeight, int valueWidth) {

        return null;
    }

    public void randomColour() {
        Random random = new Random();
        this.red = random.nextInt(255);
        this.green = random.nextInt(255);
        this.blue = random.nextInt(255);

    }

    Paint getPaint() {
        return Color.rgb(red, green, blue);
    }

    public void setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Shape Drawable() {
        return null;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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


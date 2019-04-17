package com;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.Serializable;


public class CirclObject extends ObjectClass implements Serializable {

    public CirclObject(int radius) {
        super(radius);
    }

    public CirclObject() {
    }

    @Override
    public Circle createObject(int radius) {
        this.radius = radius;
        randomColour();
        return Drawable();
    }

    @Override
    public Circle createObject() {
        randomColour();
        return Drawable();
    }

    @Override
    public Circle Drawable() {
        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setFill(getPaint());
        circle.setStroke(Color.DARKGRAY);
        circle.setStrokeWidth(2);
        circle.setCenterX(250);
        circle.setCenterY(250);
        return circle;
    }


}


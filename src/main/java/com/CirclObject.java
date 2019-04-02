package com;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.io.Serializable;


public class CirclObject extends ObjectClass implements Serializable {

    public int radius;

    public Circle circlObject(int radius) {

        this.radius = radius;
        randomColour();
        return getDrawwable();
    }

    public CirclObject() {
       // setColor(red,green,blue);
    }

    public Circle circlObject() {
        randomColour();
        return getDrawwable();
    }


    private Circle getDrawwable() {
        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setFill(getPaint());
        circle.setStroke(Color.DARKGRAY);
        circle.setStrokeWidth(2);
        circle.setCenterX(250);
        circle.setCenterY(250);
        return circle;
    }




    public void setRadius(int radius) {

        this.radius = radius;
    }

    public CirclObject(int radius) {
        this.radius = radius;
    }

    public int getRadius() {

        return radius;
    }


}

package com;


import java.io.Serializable;
import java.util.ArrayList;

public class ObjectListHandler implements Serializable {
    private ArrayList<ObjectClass> rectangles;
    private ArrayList<ObjectClass> circles;
    private String priorety;

    public String getPriorety() {
        return priorety;
    }

    public void setPriorety(String priorety) {
        this.priorety = priorety;
    }

    public ArrayList<ObjectClass> getRectangles() {
        return rectangles;
    }

    public void setRectangles(ArrayList<ObjectClass> rectangles) {
        this.rectangles = rectangles;
    }

    public ArrayList<ObjectClass> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<ObjectClass> circles) {
        this.circles = circles;
    }


}

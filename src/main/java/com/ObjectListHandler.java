package com;

import com.CirclObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjectListHandler implements Serializable {
    private ArrayList<RectangleObject> rectangles ;
    private ArrayList<CirclObject>  circles ;
    private String priorety ;

    public String getPriorety() {
        return priorety;
    }

    public void setPriorety(String priorety) {
        this.priorety = priorety;
    }

    public ArrayList<RectangleObject> getRectangles() {
        return rectangles;
    }

    public void setRectangles(ArrayList<RectangleObject> rectangles) {
        this.rectangles = rectangles;
    }

    public ArrayList<CirclObject> getCircles() {
        return circles;
    }

    public void setCircles(ArrayList<CirclObject> circles) {
        this.circles = circles;
    }



}

package com.jbima.virusspreadsimulator.object;


import com.jbima.virusspreadsimulator.Vector2d.Vector2D;

public class Position{

    private Vector2D vector;

    public Position(double x, double y) {
        this.vector = new Vector2D(x, y);
    }

    public double getX() {
        return vector.getX();
    }

    public double getY() {
        return vector.getY();
    }

    public void setX(double x) {
        vector.setX(x);
    }

    public void setY(double y) {
        vector.setY(y);
    }

    public Vector2D getVector() {
        return vector;
    }


}

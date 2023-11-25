package com.jbima.virusspreadsimulator.Vector2d;

import java.util.ArrayList;

public interface IVector2D {
    double abs();
    double cdot(IVector2D param);
    ArrayList<Double> getComponents();
    void setX(double x);
    void setY(double y);
    double getX();

    double getY();

    IVector2D copy();
}

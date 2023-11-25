package com.jbima.virusspreadsimulator.Vector2d;

import java.util.ArrayList;

public class Vector2D implements IVector2D{
    private double x;
    private double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public double cdot(IVector2D param) {
        return x * param.getComponents().get(0) + y * param.getComponents().get(1);
    }

    @Override
    public ArrayList<Double> getComponents() {
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(x);
        arrayList.add(y);
        return arrayList;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    @Override
    public IVector2D copy() {
        return new Vector2D(x, y);
    }


    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }


}

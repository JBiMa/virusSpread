package com.jbima.virusspreadsimulator.State;


import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;

import java.util.List;

public class NoSymptomsState implements IState {
    private double infectionTimer;
    public NoSymptomsState() {
        this.infectionTimer = Math.random() * 10.0 + 20.0;
    }
    @Override
    public void update(Person person, List<Person> population, double currentStep) {
        infectionTimer -= 1.0 / 25.0;
        if (infectionTimer <= 0) {
            person.setState(new ImmuneState());
        }
    }
    @Override
    public Color getColor() {
        return Color.BLUE;
    }
    public double getInfectionChance(){
        return 0.5;
    }

    @Override
    public void printInformation() {
        System.out.println("NoSymptomsState "+infectionTimer+" "+getInfectionChance());
    }

    public void setInfectionTimer(double infTim){
        this.infectionTimer=infTim;
    }
    public double getInfectionTimer(){
        return this.infectionTimer;
    }
    @Override
    public IState deepCopy() {
        NoSymptomsState copy = new NoSymptomsState();
        copy.setInfectionTimer(this.getInfectionTimer());
        return copy;
    }


}
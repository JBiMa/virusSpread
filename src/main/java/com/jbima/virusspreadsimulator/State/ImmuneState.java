package com.jbima.virusspreadsimulator.State;

import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;

import java.util.List;

public class ImmuneState implements IState {

    @Override
    public void update(Person person, List<Person> population, double currentStep) {
        // Immune people don't change
    }


    @Override
    public double getInfectionChance() {
        return 0;
    }

    @Override
    public void printInformation() {
        System.out.println("ImmuneState");
    }

    @Override
    public IState deepCopy() {
        return new ImmuneState();
    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }

}

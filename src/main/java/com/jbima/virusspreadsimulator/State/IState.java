package com.jbima.virusspreadsimulator.State;


import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public interface IState {
    public abstract Color getColor();

    void update(Person person, List<Person> population, double currentStep);

    double getInfectionChance();
//    clone state
    IState clone();
}

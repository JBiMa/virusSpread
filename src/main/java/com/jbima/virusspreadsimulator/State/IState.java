package com.jbima.virusspreadsimulator.State;


import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;

import java.util.List;

public interface IState extends Cloneable{
    Color getColor();

    void update(Person person, List<Person> population, double currentStep);

    double getInfectionChance();
    void printInformation();

    IState deepCopy();
}

package com.jbima.virusspreadsimulator.State;

import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;

import java.util.List;


public class SymptomsState implements IState {
    private double infectionTimer;

    public SymptomsState() {
        this.infectionTimer = Math.random() * 10.0 + 20.0;
    }

    @Override
    public void update(Person person, List<Person> population, double currentStep) {

        infectionTimer -= 1.0 / 25.0;
//        person.getCircle().setOpacity(0.5);
//        person.getCircle().setStroke(Color.LIGHTCORAL);
//        person.getCircle().setStrokeWidth(25);
//        person.getCircle().setFill(Color.RED);

        if (infectionTimer <= 0) {
            person.setState(new ImmuneState());
        }
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
    public double getInfectionChance(){
        return 1.0;
    }
    @Override
    public IState clone() {
        SymptomsState clonedState = new SymptomsState();
        clonedState.infectionTimer = this.infectionTimer;
        return clonedState;
    }
}

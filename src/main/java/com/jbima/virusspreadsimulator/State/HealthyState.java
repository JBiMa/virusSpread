package com.jbima.virusspreadsimulator.State;


import com.jbima.virusspreadsimulator.object.Person;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthyState implements IState{
    private static final double INFECTION_RADIUS = 25.0;
    private static final double EXPOSURE_TIME = 3.0;
    private final Map<Pair<Person, Person>, Double> exposureStartTimes = new HashMap<>();

    @Override
    public Color getColor(){
        return Color.GREEN;
    }

    @Override
    public void update(Person healthy, List<Person> population, double currentStep) {
        List<Person> nearbyInfected = findNearbyInfected(healthy, population);

        for (Person infected : nearbyInfected) {
            Pair<Person, Person> pair = new Pair<>(healthy, infected);
            Double exposureStartTime = exposureStartTimes.get(pair);

            if (exposureStartTime == null) {
                exposureStartTimes.put(pair, currentStep);
            } else {
                double timeExposed = currentStep - exposureStartTime;
//                System.out.println("Time exposed so far: " + timeExposed + " sec");
                if (timeExposed >= EXPOSURE_TIME) {
//                    System.out.println("Time exposed so far: " + timeExposed + " sec");
                    infect(healthy, currentStep);
                    break;
                }
//                System.out.println("Time exposed so far: " + timeExposed + " sec");
            }
        }
        exposureStartTimes.keySet().removeIf(pair ->
                pair.getKey().equals(healthy) &&
                        getDistanceBetweenPersons(healthy, pair.getValue()) > INFECTION_RADIUS);
    }

    @Override
    public double getInfectionChance() {
        return 0;
    }

    @Override
    public IState clone() {
        HealthyState clonedState = new HealthyState();
        clonedState.exposureStartTimes.putAll(this.exposureStartTimes);
        return clonedState;
    }

    private List<Person> findNearbyInfected(Person person, List<Person> population) {
        List<Person> nearbyInfected = new ArrayList<>();
        for (Person otherPerson : population) {
            if ((otherPerson.getState() instanceof SymptomsState || otherPerson.getState() instanceof NoSymptomsState) &&
                    getDistanceBetweenPersons(person, otherPerson) <= INFECTION_RADIUS) {
                double infectionProbability = otherPerson.getState().getInfectionChance();
                if (Math.random() < infectionProbability) {
                    nearbyInfected.add(otherPerson);
                }
            }
        }
        return nearbyInfected;
    }
    private void infect(Person person, double currentStep) {
        if (Math.random() < 0.5) {
            person.setState(new NoSymptomsState());
        } else {
            person.setState(new SymptomsState());
        }
//        System.out.println("Infection at: " + currentStep);
    }

    private double getDistanceBetweenPersons(Person p1, Person p2) {
        double xDistance = p1.getPosition().getX() - p2.getPosition().getX();
        double yDistance = p1.getPosition().getY() - p2.getPosition().getY();
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }
}

package com.jbima.virusspreadsimulator.simulation;

import com.jbima.virusspreadsimulator.object.Person;

import java.util.ArrayList;
import java.util.List;

public class SimulationMemento {
    private final ArrayList<Person> people;
    private final double currentTime;

    public SimulationMemento(ArrayList<Person> people, double currentTime) {
        this.people = deepCopyPeopleList(people);
        this.currentTime = currentTime;
    }
    private ArrayList<Person> deepCopyPeopleList(ArrayList<Person> original) {
        ArrayList<Person> copy = new ArrayList<>();
        for (Person person : original) {
            copy.add(person.deepCopy());
        }
        return copy;
    }
    public ArrayList<Person> getPeople() {
        return new ArrayList<>(people);
    }

    public double getCurrentTime() {
        return currentTime;
    }
}

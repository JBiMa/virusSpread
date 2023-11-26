package com.jbima.virusspreadsimulator.simulation;

import java.util.ArrayList;
import java.util.List;

public class SimulationCareTaker {
    private final List<SimulationMemento> mementoList = new ArrayList<>();

    public void addMemento(SimulationMemento memento) {
        mementoList.add(memento);
    }

    public SimulationMemento getMemento(int index) {
        if (index >= 0 && index < mementoList.size()) {
            return mementoList.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid memento index");
        }
    }

    public int getMementoCount() {
        return mementoList.size();
    }
}

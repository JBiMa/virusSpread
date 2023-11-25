package com.jbima.virusspreadsimulator.simulation;

import java.util.ArrayList;
import java.util.List;

public class SimulationCareTaker {
    private final List<SimulationMemento> mementoList = new ArrayList<>();

    public void addMemento(SimulationMemento memento) {
        mementoList.add(memento);
    }

    public SimulationMemento getMemento(int index) {
        return mementoList.get(index);
    }

    public int getMementoCount() {
        return mementoList.size();
    }
}

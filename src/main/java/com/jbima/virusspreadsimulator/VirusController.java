package com.jbima.virusspreadsimulator;

import com.jbima.virusspreadsimulator.simulation.Simulation;
import com.jbima.virusspreadsimulator.simulation.SimulationCareTaker;
import com.jbima.virusspreadsimulator.simulation.SimulationMemento;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class VirusController {
    @FXML
    private Pane mainPane;
    @FXML
    private Slider immunitySlider;
    @FXML
    private Label immunityPercentageLabel;
    @FXML
    private ChoiceBox<Integer> snapshotBox;
    private Simulation simulation;
    private SimulationCareTaker caretaker;


    @FXML
    private void handleStart() {
        if (simulation != null) {
            simulation.stopSimulation();
            simulation = null;
        }
        double immunityPercentage = immunitySlider.getValue();
        simulation = new Simulation(mainPane,immunityPercentage);
        caretaker= new SimulationCareTaker();
        snapshotBox.getItems().clear();
        snapshotBox.setValue(null);
    }
    public void initialize() {
        immunitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateImmunityPercentageLabel(newValue.intValue());
        });
    }

    @FXML
    private void handleStop() {
        if (simulation != null) {
            simulation.stopSimulation();
            simulation = null;
        }
    }
    @FXML
    private void handleReset() {
        if (simulation != null) {
            simulation.stopSimulation();
            simulation = null;
        }
        double immunityPercentage = immunitySlider.getValue();
        simulation = new Simulation(mainPane,immunityPercentage);
    }
    @FXML
    private void handleSave() {
        SimulationMemento memento = simulation.createMemento();
        caretaker.addMemento(memento);
        int index = caretaker.getMementoCount() - 1;
        snapshotBox.getItems().add(index);
        snapshotBox.setValue(index);
    }

    @FXML
    private void handleLoad() {
        int selectedMementoIndex = snapshotBox.getValue();
        SimulationMemento memento = caretaker.getMemento(selectedMementoIndex);
        simulation.restoreFromMemento(memento);
    }
    private void updateImmunityPercentageLabel(int sliderValue) {
        immunityPercentageLabel.setText(sliderValue + "%");
    }
}

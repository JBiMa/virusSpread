package com.jbima.virusspreadsimulator;

import com.jbima.virusspreadsimulator.simulation.Simulation;
import com.jbima.virusspreadsimulator.simulation.SimulationCareTaker;
import com.jbima.virusspreadsimulator.simulation.SimulationMemento;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

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
        immunitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateImmunityPercentageLabel(newValue.intValue()));
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
        if (simulation != null) {
            SimulationMemento memento = simulation.createMemento();
            caretaker.addMemento(memento);
            int index = caretaker.getMementoCount() - 1;
            snapshotBox.getItems().add(index);
            snapshotBox.setValue(index);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No simulation to save");
            alert.setContentText("Please start a simulation before saving");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleLoad() {
        if (simulation != null) {
            int selectedMementoIndex = snapshotBox.getValue();
            SimulationMemento memento = caretaker.getMemento(selectedMementoIndex);
            caretaker.getMemento(selectedMementoIndex).getPeople().forEach(person -> {
                System.out.println("Person position: " + person.getPosition().getX() + ", " + person.getPosition().getY()+" person speed: "+person.getMove().getSpeed().getComponents().get(0)+", "+person.getMove().getSpeed().getComponents().get(1));
                person.getState().printInformation();
            });
            simulation.restoreFromMemento(memento);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No simulation to load");
            alert.setContentText("Please start a simulation before loading");
            alert.showAndWait();
        }
    }
    private void updateImmunityPercentageLabel(int sliderValue) {
        immunityPercentageLabel.setText(sliderValue + "%");
    }
}

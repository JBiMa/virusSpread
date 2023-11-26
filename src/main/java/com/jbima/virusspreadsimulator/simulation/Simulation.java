package com.jbima.virusspreadsimulator.simulation;


import com.jbima.virusspreadsimulator.State.*;
import com.jbima.virusspreadsimulator.Vector2d.Vector2D;
import com.jbima.virusspreadsimulator.object.Person;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.jbima.virusspreadsimulator.VirusSimulationApplication.SCENE_HEIGHT;
import static com.jbima.virusspreadsimulator.VirusSimulationApplication.SCENE_WIDTH;

public class Simulation {
    private static final double FPS = 25.0;
    private static final int NUM_OBJECTS = 500;

    private static final double PERSON_RADIUS = 5.0;
    private static final double TIME_STEP = 1.0 / FPS; // 25 steps per second
    private final Pane mainPane;
    private ArrayList<Person> people;
    private double currentTime;
    private final double initialImmunity;
    private IState state;

        public Simulation(Pane mainPane, double initialImmunity) {
        this.initialImmunity = initialImmunity;
        System.out.println(initialImmunity);
        this.currentTime = 0.0;
        this.mainPane = mainPane;
        people = new ArrayList<>();
        initializePeople();
        initializeTimeline();

    }
    public void initializePeople() {
        for (int i = 0; i < NUM_OBJECTS; i++) {
            if (Math.random()*100 < initialImmunity) {
                state = new ImmuneState();
            } else {
                state = new HealthyState();
            }
            addPerson(mainPane,state);
        }
    }

    public void initializeTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000 / FPS), event -> updateSimulation())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void update() {
        currentTime += TIME_STEP;
        Random random = new Random();
        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            if (person.getPosition().getX() == 99999) {
                people.remove(i);
                mainPane.getChildren().remove(person.getCircle());
                if (Math.random()*100 < initialImmunity) {
                    state = new ImmuneState();
                } else if (random.nextDouble()<0.2){
                    state = new SymptomsState();
                }else{
                    state = new HealthyState();
                }
                addPerson(mainPane, state);
                i--;
            }
            person.move();
            person.update(people, currentTime);
        }
    }
    public void addPerson(Pane mainPane,IState state){
        Random random = new Random();
        double randomX = random.nextDouble() * (SCENE_WIDTH - 2 * PERSON_RADIUS) + PERSON_RADIUS;
        double randomY = random.nextDouble() * (SCENE_HEIGHT - 2 * PERSON_RADIUS) + PERSON_RADIUS;
        Vector2D initialPosition = new Vector2D(randomX, randomY);
        Person person = new Person(state, initialPosition);
        people.add(person);
        mainPane.getChildren().add(person.getCircle());
    }
    public void updateSimulation() {
        update();
    }
    public void stopSimulation() {
        Iterator<Person> iterator = people.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            iterator.remove();
            mainPane.getChildren().remove(person.getCircle());
        }
    }
    public double getCurrentTime() {
        return currentTime;
    }
    public ArrayList<Person> getPersonArrayList(){
        return people;
    }

    public void setPersonArrayList(ArrayList<Person> population){
        this.people = population;
    }

    public void setTime(double time) {
        this.currentTime = time;
    }
    public void printPersonPositions() {
        for (Person person : getPersonArrayList()) {
            System.out.println("Person position: " + person.getPosition().getX() + ", " + person.getPosition().getY()+" person speed: "+person.getMove().getSpeed().getComponents().get(0)+", "+person.getMove().getSpeed().getComponents().get(1));
            person.getState().printInformation();
        }
    }
    public SimulationMemento createMemento() {
        return new SimulationMemento(getPersonArrayList(), getCurrentTime());
    }
    public void restoreFromMemento(SimulationMemento memento) {
        stopSimulation();

        ArrayList<Person> restoredPeople = memento.getPeople();
        setPersonArrayList(restoredPeople);
        setTime(memento.getCurrentTime());
        this.people = new ArrayList<>();
        this.currentTime = memento.getCurrentTime();
        for (Person savedPerson : restoredPeople) {
            Person restoredPerson = new Person(savedPerson.getState().deepCopy(), savedPerson.getPosition().getVector());
            this.people.add(restoredPerson);
            this.mainPane.getChildren().add(restoredPerson.getCircle());
        }
        System.out.println("Simulation restored!");
    }

}

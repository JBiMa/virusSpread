package com.jbima.virusspreadsimulator.object;



import com.jbima.virusspreadsimulator.Vector2d.IVector2D;
import com.jbima.virusspreadsimulator.Vector2d.Vector2D;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

import static com.jbima.virusspreadsimulator.VirusSimulationApplication.SCENE_HEIGHT;
import static com.jbima.virusspreadsimulator.VirusSimulationApplication.SCENE_WIDTH;

public class Move {

    private static final double MAX_SPEED = 1;//1px/frame it is equal to 2.5m/s if 1m is 10px
    private static final double MAX_CHANGE_INTERVAL = 2.0;
    private IVector2D speed;
    private Timeline directionChangeTimeline;

    public Move() {
        initializeRandomDirectionChange();
        initializeSpeed();
    }
    public Move(IVector2D speed) {
        this.speed = speed;
    }
    private void initializeSpeed() {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI;
        double speedMagnitude = random.nextDouble() * MAX_SPEED;
        this.speed = new Vector2D(speedMagnitude * Math.cos(angle), speedMagnitude * Math.sin(angle));
    }

    public IVector2D getSpeed() {
        return speed;
    }
    public void setSpeed(IVector2D speed) {
        this.speed = speed;
    }
    private void initializeRandomDirectionChange() {
        directionChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(getRandomChangeInterval()), event -> {
                    initializeSpeed();
                    directionChangeTimeline.playFromStart();
                })
        );
        directionChangeTimeline.setCycleCount(Timeline.INDEFINITE);
        directionChangeTimeline.play();
    }

    private double getRandomChangeInterval() {
        Random random = new Random();
        return random.nextDouble() * MAX_CHANGE_INTERVAL;
    }

    public void leaveBoundary(Position position) {
        position.setX(99999);
        position.setY(99999);
        setSpeed(new Vector2D(0,0));
    }

    public void checkBoundaryCollision(Position position){
        boolean shouldReverse = Math.random() < 0.5;
        if (position.getX() - Person.PERSON_RADIUS <= 0 || position.getX() + Person.PERSON_RADIUS >= SCENE_WIDTH) {
            if (shouldReverse) {
                speed.setX(-speed.getX());
            } else {
                leaveBoundary(position);
            }
        }

        if (position.getY() - Person.PERSON_RADIUS <= 0 || position.getY() + Person.PERSON_RADIUS >= SCENE_HEIGHT) {
            if (shouldReverse) {
                speed.setY(-speed.getY());
            } else {
                leaveBoundary(position);
            }
        }
    }
}

package com.jbima.virusspreadsimulator.object;
import com.jbima.virusspreadsimulator.State.IState;
import com.jbima.virusspreadsimulator.Vector2d.IVector2D;
import com.jbima.virusspreadsimulator.Vector2d.Vector2D;
import javafx.scene.shape.Circle;

import java.util.List;
public class Person{
    public static final double PERSON_RADIUS = 5.0;
    private final Circle circle;
    private final Position position;
    private final Move move;
    private IState state;
    public Person(IState state, Vector2D initialPosition) {
        this.state = state;
        this.circle = new Circle(initialPosition.getX(), initialPosition.getY(), PERSON_RADIUS);
        this.circle.setFill(state.getColor());
        this.position = new Position(initialPosition.getX(), initialPosition.getY());
        this.move = new Move();
    }
    public Person(IState state, Vector2D initialPosition, Move move) {
        this.state = state;
        this.circle = new Circle(initialPosition.getX(), initialPosition.getY(), PERSON_RADIUS);
        this.circle.setFill(state.getColor());
        this.position = new Position(initialPosition.getX(), initialPosition.getY());
        this.move = move;
    }
    public void move() {
        IVector2D displacement = move.getSpeed();
        position.setX(position.getX() + displacement.getComponents().get(0));
        position.setY(position.getY() + displacement.getComponents().get(1));

        circle.setCenterX(position.getX());
        circle.setCenterY(position.getY());

        move.checkBoundaryCollision(position);
    }
    public void update(List<Person> people, double currentTime){
        state.update(this,people,currentTime);
    }


    public void setState(IState state){
        this.state=state;
        circle.setFill(state.getColor());
    }
    public Circle getCircle() {
        return circle;
    }
    public Move getMove(){
        return this.move;
    }
    public IState getState(){
        return this.state;
    }
    public Position getPosition(){
        return this.position;
    }
    public Person deepCopy() {
        Position newPosition = new Position(this.position.getX(), this.position.getY());
        Move newMove = new Move(move.getSpeed());
        IState newState = this.state.deepCopy();
        return new Person(newState, newPosition.getVector(),newMove);
    }
}
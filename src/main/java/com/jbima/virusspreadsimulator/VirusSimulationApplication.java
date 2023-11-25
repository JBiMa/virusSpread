package com.jbima.virusspreadsimulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.io.IOException;

public class VirusSimulationApplication extends Application {
    public static final double SCENE_WIDTH = 1200;
    public static final double SCENE_HEIGHT = 840;
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MovingObjects.fxml"));
        Parent root = loader.load();

        VirusController controller = loader.getController();
        controller.initialize();

        Scene scene = new Scene(root, SCENE_WIDTH+40, SCENE_HEIGHT+40);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Moving Objects");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
module com.jbima.virusspreadsimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jbima.virusspreadsimulator to javafx.fxml;
    exports com.jbima.virusspreadsimulator;
}
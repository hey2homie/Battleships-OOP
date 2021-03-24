package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ScorePage implements Initializable {

    @FXML
    Button exit;
    @FXML
    Label name;
    @FXML
    Label score;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void exit() {
        // TODO: Not to exit application, but to allow players play another game. The problem is to set everything fresh
        System.exit(1);
    }
}

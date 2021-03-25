package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GamePage implements Initializable {

    @FXML
    Button nextPlayer2;
    @FXML
    Label timerLabel;
    @FXML
    GridPane gridPane;
    @FXML
    Pane pane;
    @FXML
    Label name;
    @FXML
    TextArea textArea;

    @FXML
    private void toPlayer2Move(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


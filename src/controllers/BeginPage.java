package controllers;

import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BeginPage implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void toSettings(ActionEvent event) throws IOException {
        Utilities.changeScene(event, "../FXML/settings.fxml");
    }

    @FXML
    private void toQA(ActionEvent event) throws IOException {
        Utilities.changeScene(event, "../FXML/qa.fxml");
    }

    @FXML
    private void toHistory(ActionEvent event) throws IOException {
        Utilities.changeScene(event, "../FXML/history.fxml");
    }
}

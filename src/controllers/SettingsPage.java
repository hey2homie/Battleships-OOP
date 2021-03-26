package controllers;

import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPage implements Initializable{

    @FXML
    private TextField player1;
    @FXML
    private TextField player2;
    @FXML
    private Button timer60;
    @FXML
    private Button timer30;
    @FXML
    private Button setMishits;
    @FXML
    private Button setTimeSpent;

    private boolean timerSet = false;
    private boolean scoringSet = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void toPlacement(ActionEvent event) throws IOException {
        boolean[] allSet = new boolean[3];

        if (player1.getText().equals("") || player2.getText().equals("")) {
            Utilities.raiseAlert("Please write down player's names!");
        } else {
            allSet[0] = true;
            Utilities.getPlayer1().setName(player1.getText());
            Utilities.getPlayer2().setName(player2.getText());
        }
        if (!timerSet) {
            Utilities.raiseAlert("Please, set timer!");
        } else {
            allSet[1] = true;
        }
        if (!scoringSet) {
            Utilities.raiseAlert("Please, set scoring system!");
        } else {
            allSet[2] = true;
        }
        if (allSet[0] && allSet[1] && allSet[2]) {
            Utilities.changeScene(event, "../FXML/placement.fxml");
        }
    }

    @FXML
    private void setTimer30() {
        Utilities.setGameTime(30000);
        timer30.setUnderline(true);
        timer60.setUnderline(false);
        timerSet = true;
    }

    @FXML
    private void setTimer60() {
        Utilities.setGameTime(60000);
        timer60.setUnderline(true);
        timer30.setUnderline(false);
        timerSet = true;
    }

    @FXML
    private void setTimeSpent() {
        Utilities.setScoringSystem("timing");
        setTimeSpent.setUnderline(true);
        setMishits.setUnderline(false);
        scoringSet = true;
    }

    @FXML
    private void setMishits() {
        Utilities.setScoringSystem("mishit");
        setMishits.setUnderline(true);
        setTimeSpent.setUnderline(false);
        scoringSet = true;
    }
}

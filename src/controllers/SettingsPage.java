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
    TextField player1;
    @FXML
    TextField player2;
    @FXML
    Button timer60;
    @FXML
    Button timer30;
    @FXML
    Button toPlacementBoard;
    @FXML
    Button setMishits;
    @FXML
    Button setTimeSpent;

    public static int mills;
    public static String scoring;
    boolean timerSet = false;
    boolean scoringSet = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void toPlacement(ActionEvent event) throws IOException {
        boolean[] allSet = new boolean[3];

        // Players.namePlayer1 = player1.getText();
        // Players.namePlayer2 = player2.getText();

        // Check if all settings were applied

        if (player1.getText().equals("") || player2.getText().equals("")) {
            Utilities.raiseAlert("Please write down player's names!");
        } else {
            allSet[0] = true;
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
            // usedSettings = scoring.equals("timing") ? "timing" : "mishit";
            Utilities.changeScene(event, "../../stylefiles/placement1.fxml");
        }
    }

    @FXML
    private void setTimer30() {
        mills = 30000;
        timer30.setUnderline(true);
        timer60.setUnderline(false);
        timerSet = true;
    }

    @FXML
    private void setTimer60() {
        mills = 60000;
        timer60.setUnderline(true);
        timer30.setUnderline(false);
        timerSet = true;
    }

    @FXML
    private void setTimeSpent() {
        scoring = "timing";
        setTimeSpent.setUnderline(true);
        setMishits.setUnderline(false);
        scoringSet = true;
    }

    @FXML
    private void setMishits() {
        scoring = "mishit";
        setMishits.setUnderline(true);
        setTimeSpent.setUnderline(false);
        scoringSet = true;
    }
}

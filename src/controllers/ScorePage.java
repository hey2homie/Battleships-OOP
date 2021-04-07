package controllers;

import battleships.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScorePage implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label score;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText("Congratulations, " + Utilities.getWinner() + "!");
        score.setText(Utilities.getScore());
        try {
            FileWriter fileWriter = new FileWriter("src/gameHistory.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(score.getText());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ignored) {
        }
    }

    @FXML
    private void exit() {
        // TODO: Not to exit application, but to allow players play another game. The problem is to set everything fresh
        System.exit(1);
    }
}

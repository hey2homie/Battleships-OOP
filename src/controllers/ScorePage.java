package controllers;

import battleships.Player;
import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
            Utilities.setScore(" &#10;");
            bufferedWriter.write(score.getText().replace("You", Utilities.getWinner()));
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException ignored) {
        }
    }

    @FXML
    private void exit() {
        System.exit(1);
    }

    @FXML
    private void continueGame(ActionEvent event) throws IOException {
        Utilities.setPlayer1(new Player());
        Utilities.setPlayer2(new Player());
        Utilities.cleanScore();
        Utilities.changeScene(event, "../FXML/begin.fxml");
    }
}

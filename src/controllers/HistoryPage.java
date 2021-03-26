package controllers;

import battleships.Player;
import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

class HistoryPage implements Initializable {

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StringBuilder stringBuilder = new StringBuilder();  // Mutable since we append from file one line at the time
        try {   // In case file doesn't exists. IDE suggestion btw
            Scanner scanner = new Scanner(Paths.get("src/gameHistory.txt"));
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException ignored) {
        }
        textArea.setText(String.valueOf(stringBuilder).replace("&#10;", "\n")); // Adding new lines
    }

    private void toMenu(ActionEvent event) throws IOException {
        Utilities.changeScene(event, "../../stylefiles/begin.fxml");
    }
}

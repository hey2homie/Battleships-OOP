package battleships;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utilities {

    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static int gameTime = 0;
    private static String scoringSystem;
    private static String score = "";
    private static String winner;

    public static void changeScene(Event event, String scene) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nextScene = new Scene(FXMLLoader.load(Objects.requireNonNull(Utilities.class.getResource(scene))));
        stageTheEventSourceNodeBelongs.setScene(nextScene);
    }

    public static void raiseAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setGraphic(null);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(Objects.requireNonNull(Utilities.class.getResource(
                "../CSS/alert.css")).toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static int getGameTime() {
        return gameTime;
    }

    public static String getScoringSystem() {
        return scoringSystem;
    }

    public static String getWinner() {
        return winner;
    }

    public static String getScore() {
        return score;
    }

    public static void setPlayer1(Player player1) {
        Utilities.player1 = player1;
    }

    public static void setPlayer2(Player player2) {
        Utilities.player2 = player2;
    }

    public static void setGameTime(int time) {
        gameTime = time;
    }

    public static void setScoringSystem(String scoring) {
        scoringSystem = scoring;
    }

    public static void setScore(String result) {
        score += result;
    }

    public static void setWinner(String win) {
        winner = win;
    }

    public static void cleanScore() {
        score = "";
    }
}
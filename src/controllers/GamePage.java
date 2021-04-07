package controllers;

import battleships.Player;
import battleships.Timer;
import battleships.Utilities;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GamePage implements Initializable {

    @FXML
    private Button nextPlayer2;
    @FXML
    private Label timerLabel;
    @FXML
    private GridPane battleField1;
    @FXML
    private GridPane battleField2;
    @FXML
    private Label name;
    @FXML
    private TextArea textArea;

    private Player player = Utilities.getPlayer2();
    private int turn = 1;
    private Timer timer;

    @FXML
    private void toPlayer2Move(ActionEvent event) throws IOException {
        if (player.isClickAllowance()) {
            Utilities.raiseAlert("You haven't moved");
        } else {
            changeField(turn);
            timer.stop();
            timer = new Timer(Utilities.getGameTime(), turn, player, timerLabel);
            name.setText(player.getName());

            if (turn == 1) {
                    turn = 2;
                    player = Utilities.getPlayer1();
            } else if (turn == 2) {
                    turn = 1;
                    player = Utilities.getPlayer2();
            } else if (turn == 3){
                Utilities.changeScene(event, "../FXML/score.fxml");
            }

            textArea.setText(player.getGameHistory());
            putScrollBarDown(textArea);
            player.setClickAllowance(true);
            timer.newMove();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timer = new Timer(Utilities.getGameTime(), 1, player ,timerLabel);
        timer.newMove();
        Utilities.getPlayer1().getGameBoard().prepareBoard(battleField1);
        Utilities.getPlayer2().getGameBoard().prepareBoard(battleField2);
        battleField2.setVisible(false);
        name.setText(Utilities.getPlayer1().getName());
        battleField1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        battleField2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    EventHandler<MouseEvent> eventHandler = e -> {
        textArea.setText("");
        Node clickedNode = e.getPickResult().getIntersectedNode();
        player.getGameBoard().clickEvent(clickedNode, getOppositePlayer(turn));
        textArea.setText(player.getGameHistory());
        putScrollBarDown(textArea);
        if (getOppositePlayer(turn).getPlayerHealth() == 0) {
            Utilities.raiseAlert("You won!");
            turn = 3;
            nextPlayer2.setText("Finish");
            player.setClickAllowance(false);
        }
    };

    private void putScrollBarDown(TextArea textArea) {
        textArea.selectEnd();
        textArea.deselect();
    }

    private Player getOppositePlayer(int turn) {
        return turn == 1 ? Utilities.getPlayer2() : Utilities.getPlayer1();
    }

    private void changeField(int turn) {
        if (turn == 1) {
            battleField1.setVisible(false);
            battleField2.setVisible(true);
        } else {
            battleField1.setVisible(true);
            battleField2.setVisible(false);
        }
    }
}
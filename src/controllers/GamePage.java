package controllers;

import battleships.Player;
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
    Button nextPlayer2;
    @FXML
    Label timerLabel;
    @FXML
    GridPane battleField1;
    @FXML
    GridPane battleField2;
    @FXML
    Label name;
    @FXML
    TextArea textArea;

    private Player player = Utilities.getPlayer2();
    private int turn = 1;

    @FXML
    private void toPlayer2Move(ActionEvent event) throws IOException {
        if (turn == 1) {
            if (!player.isClickAllowance()) {
                turn = 2;
                player = Utilities.getPlayer1();
                battleField2.setVisible(true);
                battleField1.setVisible(false);
            } else {
                Utilities.raiseAlert("You haven't moved");
            }
        } else if (turn == 2) {
            if (!player.isClickAllowance()) {
                turn = 1;
                player = Utilities.getPlayer2();
                battleField1.setVisible(true);
                battleField2.setVisible(false);
            } else {
                Utilities.raiseAlert("You haven't moved");
            }
        } else {
            Utilities.changeScene(event, "../FXML/score.fxml");
        }
        player.setClickAllowance(true);
        name.setText(player.getName());
        textArea.setText(player.getGameHistory());
        putScrollBarDown(textArea);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        }
    };

    private void putScrollBarDown(TextArea textArea) {
        textArea.selectEnd();
        textArea.deselect();
    }

    private Player getOppositePlayer(int turn) {
        return turn == 1 ? Utilities.getPlayer2() : Utilities.getPlayer1();
    }
}


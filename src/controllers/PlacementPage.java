package controllers;

import battleships.Player;
import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlacementPage implements Initializable {

    @FXML
    private Button nextPlacement;
    @FXML
    private Label name;
    @FXML
    private GridPane battleField;
    @FXML
    private GridPane availableShip;
    @FXML
    private CheckBox orientation;
    @FXML
    private Label ship1x1;
    @FXML
    private Label ship2x1;
    @FXML
    private Label ship3x1;
    @FXML
    private Label ship4x1;

    private static final ImageView[] ships = new ImageView[4];
    private boolean vertical = false;
    private int dims;
    private Label[] labels;
    private boolean setRandomly = false;
    private Player player = Utilities.getPlayer1();
    private int turn = 1;

    @FXML
    private void toNextPlayer(ActionEvent event) throws IOException {
        if (turn == 1) {
            if (Utilities.getPlayer1().getAvailableShips() == 0) {
                battleField.getChildren().retainAll(battleField.getChildren().get(0));
                player = Utilities.getPlayer2();
                prepareBoard();
                turn = 2;
                name.setText(Utilities.getPlayer2().getName());
                nextPlacement.setText("Begin?");
                for (int i = 0; i < 4; i++) {
                    ships[i].setVisible(true);
                    labels[3 - i].setText(String.valueOf(i + 1));
                }
            } else {
                Utilities.raiseAlert(Utilities.getPlayer1().getName() + ", you didn't put all ships to board!");
            }
        } else if (turn == 2) {
            if (Utilities.getPlayer2().getAvailableShips() == 0) {
                Utilities.changeScene(event, "../FXML/game.fxml");
            } else {
                Utilities.raiseAlert(Utilities.getPlayer2().getName() + ", you didn't put all ships to board!");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels = new Label[]{ship1x1, ship2x1, ship3x1, ship4x1};
        name.setText(Utilities.getPlayer1().getName());
        orientation.getStylesheets().add(PlacementPage.class.getResource("../CSS/checkBox.css").toExternalForm());
        prepareBoard();
        for (int i = 0; i < 4; i++) {
            ships[i] = new ImageView(getClass().getResource("../images/1x1.png".replace(
                    "1x", (i + 1) + "x")).toExternalForm());

            availableShip.add(ships[i], 0, i);

            int index = i;
            ships[i].setOnDragDetected(event -> {
                dims = index;
                Dragboard db = ships[index].startDragAndDrop(TransferMode.MOVE);
                ClipboardContent cbContent = new ClipboardContent();
                if (index != 0 && vertical) {
                    cbContent.putImage(new
                            ImageView(getClass().getResource("../images/1x1.png".replace(
                            "x1", "x" + (dims + 1))).toExternalForm()).getImage());
                } else {
                    cbContent.putImage(ships[index].getImage());
                }
                db.setContent(cbContent);
                event.consume();
            });
        }

        battleField.setOnDragOver(event -> {
            if (event.getGestureSource() != battleField && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        battleField.setOnDragDropped(event -> {
            Node node = event.getPickResult().getIntersectedNode();

            if (setRandomly) {
                battleField.getChildren().retainAll(battleField.getChildren().get(0));
                player.getGameBoard().setGameBoard();
                player.setAvailableShips();
                setRandomly = false;
            }

            Integer cIndex = GridPane.getColumnIndex(node);
            Integer rIndex = GridPane.getRowIndex(node);
            int x = cIndex == null ? 0 : cIndex;
            int y = rIndex == null ? 0 : rIndex;

            int shipNum = 0;

            if (dims == 0) {
                shipNum = 6 + Integer.parseInt(labels[dims].getText());
            } else if (dims == 1) {
                shipNum = 3 + Integer.parseInt(labels[dims].getText());
            } else if (dims == 2) {
                shipNum = 1 + Integer.parseInt(labels[dims].getText());
            } else if (dims == 3) {
                shipNum = 1;
            }

            boolean placed = player.getGameBoard().addManually(y, x, Utilities.getPlayer1(), vertical, shipNum);

            if (placed) {
                player.getGameBoard().fillGridPane(false, battleField);
                player.reduceAvailableShips();
                labels[dims].setText(String.valueOf(Integer.parseInt(labels[dims].getText()) - 1));
                if (labels[dims].getText().equals("0")) {
                    ships[dims].setVisible(false);
                }
            }

            event.consume();
        });
    }

    @FXML
    private void SetRandomly() {
        setRandomly = true;
        player.getGameBoard().setRandomly(battleField);
        player.noAvailableShips();
    }

    @FXML
    private void setVertical() {
        this.vertical = orientation.isSelected();
    }

    private void prepareBoard() {
        player.getGameBoard().setGameBoard();
        player.getGameBoard().prepareBoard(battleField);
    }
}

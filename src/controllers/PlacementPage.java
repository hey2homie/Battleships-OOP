package controllers;

import battleships.GameBoard;
import battleships.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private boolean playerOneActions = true;
    private int dims;
    private Label[] labels;
    private boolean setRandomly = false;

    @FXML
    private void toNextPlayer(ActionEvent event) throws IOException {
        if (playerOneActions) {
            if (Utilities.getPlayer1().getShips().size() == 10) {
                playerOneActions = false;
            } else {
                Utilities.raiseAlert("You didn't put all ships to board!");
            }
        } else {
            for (ImageView ship : ships) {
                ship.setVisible(true);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels = new Label[]{ship1x1, ship2x1, ship3x1, ship4x1};
        name.setText(Utilities.getPlayer1().getName());
        orientation.getStylesheets().add(PlacementPage.class.getResource("../CSS/checkBox.css").toExternalForm());

        GameBoard boardPlayer1 = Utilities.getPlayer1().getGameBoard();
        GameBoard boardPlayer2 = Utilities.getPlayer2().getGameBoard();

        boardPlayer1.setGameBoard();
        boardPlayer1.setGameGrid(battleField);
        boardPlayer1.prepareBoard();
        boardPlayer2.setGameBoard();
        boardPlayer2.setGameGrid(battleField);
        boardPlayer2.prepareBoard();

        for (int i = 0; i < 4; i++) {
            ships[i] = new ImageView(getClass().getResource("../images/1x1.png".replace(
                    "1x", (i + 1) + "x")).toExternalForm());

            availableShip.add(ships[i], 0, i);

            int index = i;
            ships[i].setOnDragDetected(event -> {
                dims = index + 1;
                Dragboard db = ships[index].startDragAndDrop(TransferMode.MOVE);
                ClipboardContent cbContent = new ClipboardContent();
                if (index != 0 && vertical) {
                    cbContent.putImage(new
                            ImageView(getClass().getResource("../images/1x1.png".replace(
                            "x1", "x" + (dims))).toExternalForm()).getImage());
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
            Integer cIndex = GridPane.getColumnIndex(node); // When dropped, get the coordinates of the cursor
            Integer rIndex = GridPane.getRowIndex(node);
            int x = cIndex == null ? 0 : cIndex;
            int y = rIndex == null ? 0 : rIndex;

            event.consume();
        });
    }

    @FXML
    private void SetRandomly() {
        setRandomly = true;
        if (playerOneActions){
            Utilities.getPlayer1().getGameBoard().setRandomly();
        } else {
            Utilities.getPlayer2().getGameBoard().setRandomly();
        }
    }

    @FXML
    private void setVertical() {
        this.vertical = orientation.isSelected();
    }
}

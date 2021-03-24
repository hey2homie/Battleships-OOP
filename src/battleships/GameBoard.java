package battleships;

import javafx.scene.layout.GridPane;

public class GameBoard {

    private GridPane gameGrid;  // Visual representation
    private int[][] gameBoard;  // Logical representation

    public GameBoard() {
        this.gameGrid = new GridPane();
        this.gameBoard = new int[10][10];
    }

    public GridPane getGameGrid() {
        return gameGrid;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameGrid(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }
}

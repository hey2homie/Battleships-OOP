package battleships;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Random;

public class GameBoard {

    private GridPane gameGrid;
    private int[][] gameBoard = new int[10][10];

    public GameBoard() {
        this.gameGrid = new GridPane();
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

    public void setGameBoard() {
        this.gameBoard = new int[10][10];
    }

    private void randomPlacement() {

        Random random = new Random();
        setGameBoard();

        for (int i = 1; i < 11; i++) {
            boolean isHorizontal = random.nextInt(2) == 0;
            boolean battleShipFilled = false;

            while (!battleShipFilled) {
                int row = random.nextInt(9);
                int column = random.nextInt(9);
                int lengthOfBattleship = Utilities.getPlayer1().getShips().get(i - 1).getShipLength();

                while (gameBoard[row][column] == 1) {
                    row = random.nextInt(9);
                    column = random.nextInt(9);
                }

                int numberOfCorrectLocation = 0;

                for (int k = 0; k < lengthOfBattleship; k++) {
                    if (isHorizontal && row + k > 0 && row + k < 10) {
                        if (gameBoard[row + k][column] != 0) {
                            break;
                        }
                    } else if (!isHorizontal && column + k > 0 && column + k < 10) {
                        if (gameBoard[row][column + k] != 0) {
                            break;
                        }
                    } else {
                        break;
                    }

                    numberOfCorrectLocation++;
                }

                if (numberOfCorrectLocation == lengthOfBattleship) {
                    for (int k = 0; k < lengthOfBattleship; k++) {
                        if (isHorizontal) {
                            gameBoard[row + k][column] = i;
                            addUnavailable(row + k, column);
                        } else {
                            gameBoard[row][column + k] = i;
                            addUnavailable(row, column + k);
                        }
                    }
                    battleShipFilled = true;
                }
            }
        }
    }

    private void addUnavailable(int row, int column) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    if (gameBoard[row + i][column + j] == 0) {
                        gameBoard[row + i][column + j] = -1;
                    }
                } catch (IndexOutOfBoundsException ignore) {
                }
            }
        }
    }

    public boolean addManually(int row, int column, Player player, boolean vertical, int shipNum) {
        boolean placed = false;
        int len = player.getShips().get(shipNum - 1).getShipLength();
        try {
            if (vertical && (gameBoard[row + len - 1][column] == 0)) {
                for (int i = 0; i < len; i++) {
                    gameBoard[row + i][column] = shipNum;
                    addUnavailable(row + i, column);
                    placed = true;
                }
            }
            if (!vertical && (gameBoard[row][column + len - 1] == 0)) {
                for (int i = 0; i < len; i++) {
                    gameBoard[row][column + i] = shipNum;
                    addUnavailable(row, column + i);
                    placed = true;
                }
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        return placed;
    }

    public void setRandomly() {
        randomPlacement();
        fillGridPane(false);
    }

    public void prepareBoard() {
        fillGridPane(true);
    }

    public void fillGridPane(boolean clear) {
        this.gameGrid.getChildren().retainAll(this.gameGrid.getChildren().get(0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String color = gameBoard[i][j] == 0 || gameBoard[i][j] == -1 ? "-fx-background-color: null;" :
                        "-fx-background-color: #f44336;";
                if (clear) {
                    color = "-fx-background-color: null;";
                }
                Pane pane = new Pane();
                pane.setStyle(color);
                gameGrid.add(pane, j, i);
            }
        }
    }
}

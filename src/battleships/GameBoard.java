package battleships;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

    private int[][] gameBoard = new int[10][10];

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

    public boolean addManually(int row, int column, int len, boolean vertical, int shipNum) {
        boolean placed = false;

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

    public void setRandomly(GridPane gameGrid) {
        setGameBoard();
        randomPlacement();
        fillGridPane(false, gameGrid);
    }

    public void prepareBoard(GridPane gameGrid) {
        fillGridPane(true, gameGrid);
    }

    public void fillGridPane(boolean clear, GridPane gameGrid) {
        gameGrid.getChildren().retainAll(gameGrid.getChildren().get(0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String color = gameBoard[i][j] == 0 || gameBoard[i][j] == -1 ? "-fx-background-color: null;" :
                        "-fx-background-color: RosyBrown;";

                if (clear) {
                    color = "-fx-background-color: null;";
                }

                Pane pane = new Pane();
                pane.setStyle(color + " -fx-border-color: #827670");
                gameGrid.add(pane, j, i);
            }
        }
    }

    public void clickEvent(Node clickedNode, Player player) {

        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);

        if (player.isClickAllowance() && gameBoard[rowIndex][colIndex] != -2) {
            player.addHistory((rowIndex + 1) + ":" + (colIndex + 1));

            if (Arrays.stream(new int[] {-2, -1, 0}).noneMatch(i -> i == gameBoard[rowIndex][colIndex])) {
                player.takeDamage();
                player.getShips().get(gameBoard[rowIndex][colIndex] - 1).takeDamage();

                if (player.getShips().get(gameBoard[rowIndex][colIndex] -1).getShipHealth() == 0) {
                    player.addHistory(" Sink"  + "\n");
                } else {
                    player.addHistory(" Hit"  + "\n");
                }

                clickedNode.setStyle("-fx-background-color: RosyBrown; -fx-border-color: #827670");
            } else if (gameBoard[rowIndex][colIndex] == -1 || gameBoard[rowIndex][colIndex] == 0) {
                if (player.getPlayerHealth() != 0) {
                    player.setClickAllowance(false);
                    player.addMisHits();
                    player.addHistory(" Miss" + "\n");
                    clickedNode.setStyle("-fx-background-color: RoyalBlue; -fx-border-color: #827670");
                }
            }

            gameBoard[rowIndex][colIndex] = -2;
        }
    }
}
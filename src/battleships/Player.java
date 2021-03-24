package battleships;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Each instance of this class represents individual player and all necessary information.
 */
public class Player {

    private int playerHealth;
    private List<Ship> ships;
    private String name;
    private int mishits;
    private int timeSpent;
    private TextArea gameHistory;
    private boolean clickAllowance;
    private GameBoard gameBoard;

    public Player() {
        this.playerHealth = 20;
        this.mishits = 0;
        this.timeSpent = 0;
        this.clickAllowance = true;
        this.ships = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            if (i < 5) {
                ships.add(new Ship(1));
            } else if (i < 8) {
                ships.add(new Ship(2));
            } else if (i < 10) {
                ships.add(new Ship(3));
            } else {
                ships.add(new Ship(4));
            }
        }
        this.gameBoard = new GameBoard();
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public String getName() {
        return name;
    }

    public int getMishits() {
        return mishits;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public TextArea getGameHistory() {
        return gameHistory;
    }

    public boolean isClickAllowance() {
        return clickAllowance;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void takeDamage() {
        this.playerHealth -= 1;
    }

    public void reduceShips() {
        throw new IllegalArgumentException("Method is not implemented yet");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMisHits() {
        this.mishits += 1;
    }

    public void addTime(int time) {
        this.timeSpent += time;
    }

    public void addHistory( ) {
        throw new IllegalArgumentException("Method is not implemented yet");
    }

    public void setClickAllowance(boolean clickAllowance) {
        this.clickAllowance = clickAllowance;
    }
}

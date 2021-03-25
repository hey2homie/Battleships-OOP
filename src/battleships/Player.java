package battleships;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

/**
 * Each instance of this class represents individual player and all necessary information.
 */
public class Player {

    private int playerHealth;
    private final ArrayList<Ship> ships;
    private String name;
    private int mishits;
    private int timeSpent;
    private TextArea gameHistory;
    private boolean clickAllowance;
    private final GameBoard gameBoard;

    public Player() {
        this.playerHealth = 20;
        this.mishits = 0;
        this.timeSpent = 0;
        this.clickAllowance = true;
        this.ships = new ArrayList<>();
        this.gameBoard = new GameBoard();

        for (int i = 0; i < 10; i++) {
            if (i < 1) {
                ships.add(new Ship(4));
            } else if (i < 3) {
                ships.add(new Ship(3));
            } else if (i < 6) {
                ships.add(new Ship(2));
            } else {
                ships.add(new Ship(1));
            }
        }
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

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void removeShips() {
        this.ships.clear();
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

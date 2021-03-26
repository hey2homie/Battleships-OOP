package battleships;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int playerHealth = 20;
    private final ArrayList<Ship> ships = new ArrayList<>();
    private String name;
    private int mishits = 0;
    private int timeSpent = 0;
    private String gameHistory = "";
    private boolean clickAllowance = true;
    private final GameBoard gameBoard = new GameBoard();
    private int availableShips = 10;

    public Player() {
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

    public String getGameHistory() {
        return gameHistory;
    }

    public int getAvailableShips() {
        return availableShips;
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

    public void setName(String name) {
        this.name = name;
    }

    public void addMisHits() {
        this.mishits += 1;
    }

    public void addTime(int time) {
        this.timeSpent += time;
    }

    public void addHistory(String message) {
        this.gameHistory += message;
    }

    public void setClickAllowance(boolean clickAllowance) {
        this.clickAllowance = clickAllowance;
    }

    public void noAvailableShips() {
        this.availableShips = 0;
    }

    public void setAvailableShips() {
        this.availableShips = 10;
    }

    public void reduceAvailableShips() {
        this.availableShips--;
    }
}

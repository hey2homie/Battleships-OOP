package battleships;

import gui.BattleShips;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.List;

/**
 * Each instance of this class represents individual player and all necessary infromation.
 */
public class Players {

    private int playerHealth;
    private List<BattleShips>[] ships;
    private String name;
    private GridPane board;
    private int mishits;
    private int timeSpent;
    private TextArea gameHistory;
    private boolean clickAllowance;

    public Players() {
        this.playerHealth = 20;
        this.mishits = 0;
        this.timeSpent = 0;
        this.clickAllowance = true;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public List<BattleShips>[] getShips() {
        return ships;
    }

    public String getName() {
        return name;
    }

    public GridPane getBoard() {
        return board;
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

    public void takeDamage() {
        this.playerHealth -= 1;
    }

    public void reduceShips() {
        throw new IllegalArgumentException("Method is not implemented yet");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(GridPane board) {
        this.board = board;
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

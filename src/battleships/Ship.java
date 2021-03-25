package battleships;

/**
 * Each instance of this class represent ship.
 */
public class Ship {

    private final int shipLength;
    private int shipHealth;
    private final int xLength;
    private final int yLength = 3;


    public Ship(int shipLength) {
        this.shipLength = shipLength;
        this.shipHealth = shipLength;
        this.xLength = shipLength + 2;
    }

    public int getShipLength() {
        return shipLength;
    }

    public int getShipHealth() {
        return shipHealth;
    }

    public int getxLength() {
        return xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public void takeDamage() {
        this.shipHealth -= 1;
    }
}

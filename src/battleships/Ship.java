package battleships;

/**
 * Each instance of this class represent ship.
 */
public class Ship {

    private final int shipLength;
    private int shipHealth;

    public Ship(int shipLength) {
        this.shipLength = shipLength;
        this.shipHealth = shipLength;
    }

    public int getShipLength() {
        return shipLength;
    }

    public int getShipHealth() {
        return shipHealth;
    }

    public void takeDamage() {
        this.shipHealth -= 1;
    }
}

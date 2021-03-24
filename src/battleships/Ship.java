package battleships;

import javafx.scene.image.ImageView;

/**
 * Each instance of this class represent ship.
 */
public class Ship {

    private ImageView shipRepresentation;
    private int[] shipLocation;
    private int[] unavailableArea;
    private final int shipLength;
    private int shipHealth;


    public Ship(int shipLength) {
        this.shipLength = shipLength;
        switch (shipLength) {
            case 1 -> {
                this.shipRepresentation =
                        new ImageView(getClass().getResource("../../images/1x1.png").toExternalForm());
                this.shipHealth = 1;
            }
            case 2 -> {
                this.shipRepresentation =
                        new ImageView(getClass().getResource("../../images/2x1.png").toExternalForm());
                this.shipHealth = 2;
            }
            case 3 -> {
                this.shipRepresentation =
                        new ImageView(getClass().getResource("../../images/3x1.png").toExternalForm());
                this.shipHealth = 3;
            }
            case 4 -> {
                this.shipRepresentation =
                        new ImageView(getClass().getResource("../../images/4x1.png").toExternalForm());
                this.shipHealth = 4;
            }
        }
    }

    public ImageView getShipRepresentation() {
        return shipRepresentation;
    }

    public int[] getShipLocation() {
        return shipLocation;
    }

    public int[] getUnavailableArea() {
        return unavailableArea;
    }

    public int getShipLength() {
        return shipLength;
    }

    public void takeDamage() {
        this.shipHealth -= 1;
    }
}

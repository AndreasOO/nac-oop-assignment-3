package TileObject;

import javax.swing.*;

public class Tile {
    int number;
    boolean isEmpty;

    Point locationOnBoard;

    public boolean checkAdjecencyToEmtpyTile() {
        return false;
    }

    public void swapTiles() {

    }

    public void assignLocation(int x, int y) {
        locationOnBoard = new Point(x, y);
    }

    public Tile(boolean isEmpty, int number) {
        this.isEmpty = isEmpty;
        this.number = number;
    }

    public Point getLocationOnBoard() {
        return locationOnBoard;
    }

    public int getNumber() {
        return number;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

}

package TileObject;

public class Tile {
    private int number;
    private boolean isEmpty;
    private Point locationOnBoard;


    public Tile(boolean isEmpty, int number) {
        this.isEmpty = isEmpty;
        this.number = number;
    }

    public boolean isAdjacentToEmptyTile(Tile emptyTile) {
        int x1 = this.locationOnBoard.getX();
        int y1 = this.locationOnBoard.getY();
        int x2 = emptyTile.locationOnBoard.getX();
        int y2 = emptyTile.locationOnBoard.getY();

        return Math.abs(x1-x2) + Math.abs(y1-y2) == 1;
    }


    public void assignLocation(int x, int y) {
        locationOnBoard = new Point(x, y);
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

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

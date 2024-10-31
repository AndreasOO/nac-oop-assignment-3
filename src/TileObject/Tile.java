package TileObject;

public class Tile {
    int number;
    boolean isEmpty;

    Point locationOnBoard;


    public boolean isAdjacentToEmptyTile(Tile emptyTile) {
        int x1 = this.locationOnBoard.getX();
        int y1 = this.locationOnBoard.getY();
        int x2 = emptyTile.locationOnBoard.getX();
        int y2 = emptyTile.locationOnBoard.getY();

        int resultX = x1 - x2;
        int resultY = y1 - y2;

        int resultTotal = Math.abs(resultX) + Math.abs(resultY);

        return resultTotal == 1;
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

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

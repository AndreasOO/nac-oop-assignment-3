package TileObject;

public class Point {
    private int xLocation;
    private int yLocation;

    public Point(int xLocation, int yLocation) {
        setX(xLocation);
        setY(yLocation);
    }

    public int getX() {
        return xLocation;
    }

    public void setX(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getY() {
        return yLocation;
    }

    public void setY(int yLocation) {
        this.yLocation = yLocation;
    }
}



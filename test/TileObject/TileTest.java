package TileObject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    Tile testTile1 = new Tile(false, 1);
    Tile testTile2 = new Tile(false, 2);
    Tile testTile3 = new Tile(false, 3);
    Tile testTileEmpty = new Tile(true, 4);

    @Test
    void checkAdjecencyToEmtpyTile() {
        testTile1.assignLocation(2, 3);
        testTile2.assignLocation(4, 1);
        testTile3.assignLocation(4, 3);
        testTileEmpty.assignLocation(3, 3);

        assertTrue(testTile1.isAdjacentToEmptyTile(testTileEmpty));
        assertTrue(testTile3.isAdjacentToEmptyTile(testTileEmpty));
        assertFalse(testTile2.isAdjacentToEmptyTile(testTileEmpty));
    }
}
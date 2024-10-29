import GUIObject.GUI;
import TileObject.Tile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final GUI gui;
    private final Map<JButton, Tile> tiles;

    public Board() {
        gui = new GUI(4,4);
        gui.init();
        tiles = gui.getMapOfTiles();
        addEventListeners();
    }

    public void addEventListeners( ) {
        gui.getResetButton().addActionListener(e -> {
           gui.resetGame();
        });
    }

    /*
    public void trySwapTilesOrIgnore() {
        public Tile getTileClicked(input) {
            Tile tile = gotTileFromJPanelActionEvent;
        }
        Tile.checkAdjecencyToEmtpyTile();
        Tile.swapTiles();
        public void checkVictory() {

        }
    }*/

}

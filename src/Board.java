import GUIObject.GUI;
import TileObject.Tile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Board {
    private final GUI gui;
    private Map<JButton, Tile> tiles;
    private int boardHeight;
    private int boardWidth;

    public Board() {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("properties-dir/board-size.properties"));
            boardHeight = Integer.parseInt(properties.getProperty("height"));
            boardWidth = Integer.parseInt(properties.getProperty("width"));

        } catch (IOException e) {
            e.printStackTrace();
            boardHeight = 4;
            boardWidth = 4;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            boardHeight = 4;
            boardWidth = 4;
        }




        gui = new GUI(boardHeight,boardWidth);
        gui.init();
        tiles = gui.getMapOfTiles();
        addEventListeners();
    }

    public void addEventListeners( ) {
        gui.getResetButton().addActionListener(e -> {
           gui.resetGame();
           initializeButtonListeners();

        });

        initializeButtonListeners();

        gui.getPrepareVictoryButton().addActionListener(e -> {
            prepareVictory();
        });


    }

    private void initializeButtonListeners() {
        for (Map.Entry<JButton, Tile> entry : tiles.entrySet()) {
            entry.getKey().addActionListener(e -> {
                System.out.println(entry.getKey().getText() + " Tile: " + entry.getValue().getNumber());

                Tile pressedTile = tiles.get(entry.getKey());
                Tile emptyTile = tiles.values().stream().filter(Tile::isEmpty).findFirst().get();

                boolean isAdjacent = false;
                isAdjacent = pressedTile.checkAdjecencyToEmtpyTile(emptyTile);

                JButton emptyTileButton = null;

                if (isAdjacent) {
                    for (Map.Entry<JButton, Tile> entry2 : tiles.entrySet()) {
                        if (entry2.getValue().isEmpty()) {
                            emptyTileButton = entry2.getKey();
                        }
                    }
                    int pressedTempX = pressedTile.getLocationOnBoard().getX();
                    int pressedTempY = pressedTile.getLocationOnBoard().getY();
                    int emptyTileTempX = emptyTile.getLocationOnBoard().getX();
                    int emptyTileTempY = emptyTile.getLocationOnBoard().getY();
                    pressedTile.assignLocation(emptyTileTempX, emptyTileTempY);
                    emptyTile.assignLocation(pressedTempX, pressedTempY);

                    emptyTileButton.setText(String.valueOf(pressedTile.getNumber()));
                    entry.getKey().setText("");

                    Tile tempTile = pressedTile;
                    tiles.replace(entry.getKey(), emptyTile);
                    tiles.put(emptyTileButton, tempTile);

                    checkVictory();
                }




                System.out.println(String.format("Pressed tile location: x:%d  y:%d", pressedTile.getLocationOnBoard().getX(), pressedTile.getLocationOnBoard().getY()));
                System.out.println(String.format("Empty tile location: x:%d  y:%d", emptyTile.getLocationOnBoard().getX(), emptyTile.getLocationOnBoard().getY()));

                System.out.println("Tile is adjacent:" + isAdjacent);
            });
        }
    }

    private void checkVictory() {
        System.out.println("Checking victory");
        boolean victory = false;
        for (Tile tile : tiles.values()) {
            int placement = ((tile.getLocationOnBoard().getY() - 1) * boardWidth) + tile.getLocationOnBoard().getX();
            if (placement != tile.getNumber()) {
                victory = false;
                break;
            }
            victory = true;
        }
        if(victory) {
            JOptionPane.showMessageDialog(gui.getFrame(), String.format("%s", victory ? "Victory!" : "No victory..."));
        }
    }

    private void prepareVictory() {
        System.out.println("Preparing victory");
        for (Map.Entry<JButton, Tile> entry : tiles.entrySet()) {
            int placement = ((entry.getValue().getLocationOnBoard().getY() - 1)*boardWidth) + entry.getValue().getLocationOnBoard().getX();
            entry.getKey().setText(String.format("%d" , placement)); // equals coordinates
            entry.getValue().setNumber(placement);
            entry.getValue().setEmpty(false);

        }
        for (Map.Entry<JButton, Tile> entry : tiles.entrySet()) {
            if (entry.getKey().getText().equals(String.valueOf(boardHeight*boardWidth))) {
                entry.getValue().setEmpty(true);
                entry.getKey().setText("");
            }
            System.out.println(String.format("Button with text: %s is connected to Tile with number %d - Loc: x:%d y:%d", entry.getKey().getText(), entry.getValue().getNumber(), entry.getValue().getLocationOnBoard().getX(), entry.getValue().getLocationOnBoard().getY()));
        }

    }

}

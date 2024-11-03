import GUIObject.GUI;
import TileObject.Tile;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Board {
    private final GUI gui;
    private final Map<JButton, Tile> buttonAndTilePairs;
    private int boardHeight;
    private int boardWidth;

    public Board() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("properties-dir/board-size.properties"));
            boardHeight = Integer.parseInt(properties.getProperty("height"));
            boardWidth = Integer.parseInt(properties.getProperty("width"));

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            boardHeight = 4;
            boardWidth = 4;
        }
        gui = new GUI(boardHeight,boardWidth);
        gui.init();
        buttonAndTilePairs = gui.getMapOfTiles();
    }

    public void runProgram() {
        addEventListeners();
    }

    public void addEventListeners( ) {

        addTileButtonListeners();

        gui.getResetButton().addActionListener(e -> {
            gui.resetGame();
            addTileButtonListeners();
        });


        gui.getPrepareVictoryButton().addActionListener(e -> {
            prepareVictory();
        });
    }

    private void addTileButtonListeners() {
        for (Map.Entry<JButton, Tile> entry : buttonAndTilePairs.entrySet()) {

            JButton pressedTileButton = entry.getKey();

            pressedTileButton.addActionListener(e -> {
                JButton emptyTileButton = gui.getEmptyTileButton();
                Tile pressedTile = buttonAndTilePairs.get(pressedTileButton);
                Tile emptyTile = buttonAndTilePairs.get(emptyTileButton);



                if (pressedTile.isAdjacentToEmptyTile(emptyTile)) {
                    int pressedTileTempX = pressedTile.getLocationOnBoard().getX();
                    int pressedTileTempY = pressedTile.getLocationOnBoard().getY();

                    pressedTile.assignLocation(emptyTile.getLocationOnBoard().getX(), emptyTile.getLocationOnBoard().getY());
                    emptyTile.assignLocation(pressedTileTempX, pressedTileTempY);

                    emptyTileButton.setText(String.valueOf(pressedTile.getNumber()));
                    pressedTileButton.setText("");

                    buttonAndTilePairs.replace(pressedTileButton, emptyTile);
                    buttonAndTilePairs.put(emptyTileButton, pressedTile);

                    gui.setEmptyTileButton(pressedTileButton);

                    checkVictory();
                }


            });
        }
    }

    private void checkVictory() {
        boolean victory = true;
        for (Tile tile : buttonAndTilePairs.values()) {
            int expectedWinningPlacement = ((tile.getLocationOnBoard().getY() - 1) * boardWidth) + tile.getLocationOnBoard().getX();
            if (expectedWinningPlacement != tile.getNumber()) {
                victory = false;
                break;
            }
        }
        if(victory) {
            JOptionPane.showMessageDialog(gui.getFrame(), String.format("%s", victory ? "Victory!" : "No victory..."));
        }
    }

    private void prepareVictory() {
        for (Map.Entry<JButton, Tile> entry : buttonAndTilePairs.entrySet()) {
            int expectedWinningPlacement = ((entry.getValue().getLocationOnBoard().getY() - 1)*boardWidth) + entry.getValue().getLocationOnBoard().getX();
            entry.getKey().setText(String.format("%d" , expectedWinningPlacement));
            entry.getValue().setNumber(expectedWinningPlacement);
            entry.getValue().setEmpty(false);

        }
        for (Map.Entry<JButton, Tile> entry : buttonAndTilePairs.entrySet()) {
            if (entry.getKey().getText().equals(String.valueOf(boardHeight*boardWidth))) {
                entry.getValue().setEmpty(true);
                entry.getKey().setText("");
                gui.setEmptyTileButton(entry.getKey());
            }
        }
    }
}

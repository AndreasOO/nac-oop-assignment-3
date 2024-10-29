package GUIObject;

import TileObject.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GUI {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final JPanel bottomPanel;
    private final JPanel tilesPanel;
    private final JButton resetButton;
    private final Map<JButton, Tile> mapOfTiles;
    private final int height;
    private final int width;

    public GUI(int height, int width) {
        frame = new JFrame();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
        tilesPanel = new JPanel();
        resetButton = new JButton("Reset Game");
        mapOfTiles = new HashMap<>();
        this.height = height;
        this.width = width;

    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(mainPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(tilesPanel, BorderLayout.CENTER);

        bottomPanel.setLayout(new GridLayout(1,1));
        bottomPanel.add(resetButton);

        tilesPanel.setLayout(new GridLayout(this.height,this.width));

        populateTiles();



    }

    private void populateTiles() {
        ArrayList<Tile> listOfTiles = createTiles(this.height, this.width);
        Random randomIndexGenerator = new Random();
        int randomIndexOfTileList;

        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= width; col++) {
                randomIndexOfTileList = randomIndexGenerator.nextInt(listOfTiles.size());

                Tile tileFromList = listOfTiles.remove(randomIndexOfTileList);
                tileFromList.assignLocation(row, col);

                JButton button = new JButton(String.format("%s", tileFromList.isEmpty() ? ""
                                                                                        : String.valueOf(tileFromList.getNumber())));
                tilesPanel.add(button);
                mapOfTiles.put(button, tileFromList);
            }
        }
    }

    private ArrayList<Tile> createTiles(int height, int width) {
        ArrayList<Tile> listOfTiles = new ArrayList<>();
        listOfTiles.add(new Tile(true, 0));

        for (int i = 1; i < height*width; i++) {
            listOfTiles.add(new Tile(false, i));
        }
        return listOfTiles;
    }

    public void resetGame() {
        mapOfTiles.clear();
        tilesPanel.removeAll();
        populateTiles();
        frame.revalidate();
        frame.repaint();
    }

    public Map<JButton, Tile> getMapOfTiles() {
        return mapOfTiles;
    }

    public JButton getResetButton() {
        return resetButton;
    }



}

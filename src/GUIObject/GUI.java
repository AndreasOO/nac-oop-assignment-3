package GUIObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GUI {
    JFrame frame;
    JPanel mainPanel;
    JPanel bottomPanel;
    JPanel tilesPanel;
    JButton resetButton;
    Map<JButton, String> mapOfTiles;
    int height;
    int width;

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

        for (Map.Entry<JButton, String> entry : mapOfTiles.entrySet()) {
            System.out.println(entry.getKey().getText() + " --- " + entry.getValue());
        }


    }

    private void populateTiles() {
        ArrayList<String> listOfTiles = createTiles(this.height, this.width);
        Random rand = new Random();
        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= width; col++) {

                int index = rand.nextInt(listOfTiles.size());
                String tileFromList = listOfTiles.remove(index);
                String createdTile = String.format("%s - Loc: x= %d : y= %d", tileFromList, col, row);
                JButton button = new JButton(createdTile);
                tilesPanel.add(button);
                mapOfTiles.put(button, createdTile);
            }
        }
    }

    private ArrayList<String> createTiles(int height, int width) {
        ArrayList<String> listOfTiles = new ArrayList<>();
        listOfTiles.add("Empty tile");
        for (int i = 1; i < height*width; i++) {
            listOfTiles.add("Tile: " + i);
        }
        return listOfTiles;
    }
}

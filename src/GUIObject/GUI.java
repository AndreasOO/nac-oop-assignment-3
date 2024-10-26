package GUIObject;

import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame;
    JPanel mainPanel;
    JLabel testLabel;

    public GUI() {
        frame = new JFrame();
        mainPanel = new JPanel();
        testLabel = new JLabel("Test text");
    }

    public void init() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(mainPanel);

        mainPanel.add(testLabel);

        testLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    }
}

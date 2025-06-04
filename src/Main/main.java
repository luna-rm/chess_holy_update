package Main;
//https://www.youtube.com/watch?v=jzCxywhTAUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class main {
    public static JFrame frame = new JFrame("Chess - The Holy Update");

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.lunchGame();
    }


}
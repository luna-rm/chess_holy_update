package Main;
//https://www.youtube.com/watch?v=jzCxywhTAUI

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class main extends JFrame {

    main(){
        this.setTitle("Chess - The Holy Update");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        GamePanel panel = new GamePanel();
        this.add(panel);
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        for (int i = 1; i <= 4; i++) {
            String tecla = String.valueOf(i);
            int valorModo = i;

            inputMap.put(KeyStroke.getKeyStroke(tecla), "modo" + tecla);
            actionMap.put("modo" + tecla, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GamePanel.moveChosen = valorModo;
                    GamePanel.reqDivinity[0] = 0;
                    GamePanel.reqDivinity[1] = 0;
                    GamePanel.reqSlay[0] = 0;
                    GamePanel.reqSlay[1] = 0;
                    GamePanel.reqSin[0] = 0;
                    GamePanel.reqSin[1] = 0;
                }
            });
        }

        panel.lunchGame();

    }

    public static void main(String[] args) {
        new main();
    }

}
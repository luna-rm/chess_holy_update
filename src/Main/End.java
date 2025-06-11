package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class End extends JFrame {

    public End() {
        this.setTitle("Game Over");
        ImageIcon icon = new ImageIcon(getClass().getResource("./icon.png"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JLabel label = new JLabel("", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                super.paintComponent(g2);
            }
        };

        try {
            InputStream fontStream = getClass().getResourceAsStream("pixel_font.ttf");
            if (fontStream != null) {
                Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(18f);
                label.setFont(pixelFont);
            } else {
                System.err.println("Font file not found in resources!");
                label.setFont(new Font("Monospaced", Font.PLAIN, 18)); // Fallback
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            label.setFont(new Font("Monospaced", Font.PLAIN, 18));
        }

        label.setForeground(Color.WHITE);

        if (GamePanel.gameover == GamePanel.BLACK) {
            contentPane.setBackground(new Color(52, 49, 69));
            label.setText("Black Wins");
        } else {
            contentPane.setBackground(new Color(129, 137, 179));
            label.setText("White Wins");
        }

        contentPane.add(label, BorderLayout.CENTER);
        this.setVisible(true);
    }
}

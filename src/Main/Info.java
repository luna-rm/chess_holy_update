package Main;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Info extends JFrame {
    JLabel title = new JLabel();
    JLabel labelFast = new JLabel();
    JLabel labelSlay = new JLabel();
    JLabel labelSin = new JLabel();
    JLabel labelDivinity = new JLabel();
    JTextArea labelDesc = new JTextArea();

    public Info() {
        setTitle("Info");
        setSize(480, 300);
        ImageIcon icon = new ImageIcon(getClass().getResource("./icon.png"));
        this.setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Background color based on theme
        Color background = GamePanel.currentColor == GamePanel.BLACK ? new Color(52, 49, 69) : new Color(129, 137, 179);
        getContentPane().setBackground(background);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Load pixel font
        Font pixelFont = new Font("Monospaced", Font.BOLD, 12); // fallback
        try {
            InputStream is = getClass().getResourceAsStream("pixel_font.ttf");
            if (is != null) {
                pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(14f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(background);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title Label
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setFont(pixelFont.deriveFont(Font.BOLD, 16f));
        title.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(title);

        // Line under title
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setMaximumSize(new Dimension(280, 3));
        line.setForeground(Color.WHITE);
        line.setBackground(Color.WHITE);
        content.add(Box.createVerticalStrut(5));
        content.add(line);

        labelFast.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFast.setFont(pixelFont);
        labelFast.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelFast);

        labelSlay.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSlay.setFont(pixelFont);
        labelSlay.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelSlay);

        labelSin.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSin.setFont(pixelFont);
        labelSin.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelSin);

        labelDivinity.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDivinity.setFont(pixelFont);
        labelDivinity.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelDivinity);

        labelDesc.setFont(pixelFont);
        labelDesc.setForeground(Color.WHITE);
        labelDesc.setBackground(background);
        labelDesc.setWrapStyleWord(true);
        labelDesc.setLineWrap(true);
        labelDesc.setEditable(false);
        labelDesc.setFocusable(false);
        labelDesc.setOpaque(false);
        labelDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));  // Optional, for spacing
        content.add(labelDesc);

        setContentPane(content);
    }

    public void reload() {
        Color background = GamePanel.currentColor == GamePanel.BLACK ? new Color(52, 49, 69) : new Color(129, 137, 179);
        getContentPane().setBackground(background);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        title.setText(GamePanel.activeMovement.piece + "(" + GamePanel.activeMovement.number + ")");
        if(GamePanel.activeMovement.isFast == 1){
            labelFast.setText("Fast");
        } else {
            labelFast.setText("Slow");
        }
        labelSlay.setText("Slay:" + GamePanel.activeMovement.reqSlay);
        labelSin.setText("Sin:" + GamePanel.activeMovement.reqSin);
        labelDivinity.setText("Divinity:" + GamePanel.activeMovement.reqDiv);
        labelDesc.setText(GamePanel.activeMovement.desc);

        if(!this.isVisible()){
            this.setVisible(true);
        }
    }
}

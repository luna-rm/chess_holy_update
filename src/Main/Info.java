package Main;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Info extends JFrame {
    JPanel content = new JPanel();

    JLabel title = new JLabel();
    JLabel labelType = new JLabel();
    JLabel labelFast = new JLabel();
    JLabel labelSlay = new JLabel();
    JLabel labelSin = new JLabel();
    JLabel labelDivinity = new JLabel();
    JTextArea labelDesc = new JTextArea();
    JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
    JPanel require = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

    public Info() {
        setTitle("Info");
        setSize(400, 250);
        ImageIcon icon = new ImageIcon(getClass().getResource("./icon.png"));
        this.setIconImage(icon.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Background color based on theme
        Color background = new Color(52, 49, 69);
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

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(background);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        require.setBackground(background);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setFont(pixelFont.deriveFont(Font.BOLD, 16f));
        title.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(title);

        line.setMaximumSize(new Dimension(280, 3));
        line.setForeground(Color.WHITE);
        line.setBackground(Color.WHITE);
        content.add(Box.createVerticalStrut(5));
        content.add(line);

        line.setVisible(false);

        labelType.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelType.setFont(pixelFont);
        labelType.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelType);

        labelSlay.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSlay.setFont(pixelFont);
        labelSlay.setForeground(Color.WHITE);
        require.add(labelSlay);

        labelSin.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSin.setFont(pixelFont);
        labelSin.setForeground(new Color(189, 43, 58));
        require.add(labelSin);

        labelDivinity.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDivinity.setFont(pixelFont);
        labelDivinity.setForeground(new Color(47, 81, 208));
        require.add(labelDivinity);

        content.add(Box.createVerticalStrut(5));
        content.add(require);

        labelFast.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFast.setFont(pixelFont);
        labelFast.setForeground(Color.WHITE);
        content.add(Box.createVerticalStrut(10));
        content.add(labelFast);

        labelDesc.setFont(pixelFont);
        labelDesc.setForeground(Color.WHITE);
        labelDesc.setBackground(background);
        labelDesc.setWrapStyleWord(true);
        labelDesc.setLineWrap(true);
        labelDesc.setEditable(false);
        labelDesc.setFocusable(false);
        labelDesc.setOpaque(false);
        labelDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(10));
        content.add(labelDesc);

        setContentPane(content);

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        require.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelType.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelFast.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSlay.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelSin.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDivinity.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    public void reload() {
        if(GamePanel.activeMovement.noMov){
            return;
        }

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        content.setVisible(true);
        if(GamePanel.activeMovement.number == -1){
            content.setVisible(false);
        }

        title.setText(GamePanel.activeMovement.piece + "(" + GamePanel.activeMovement.number + ")");
        line.setVisible(true);

        if(GamePanel.activeMovement.type == 0){
            labelType.setText("War Balance");
            labelType.setForeground(Color.WHITE);
        } else if(GamePanel.activeMovement.type == 1){
            labelType.setText("Unholy Ritual");
            labelType.setForeground(new Color(189, 43, 58));
        } else if(GamePanel.activeMovement.type == 2){
            labelType.setText("Holy Power");
            labelType.setForeground(new Color(47, 81, 208));
        }

        require.setVisible(true);
        labelSlay.setText(GamePanel.activeMovement.reqSlay + " ");
        labelSin.setText(GamePanel.activeMovement.reqSin + " ");
        labelDivinity.setText(GamePanel.activeMovement.reqDiv + "");

        if(GamePanel.activeMovement.isFast == 1){
            labelFast.setText("Fast");
        } else if(GamePanel.activeMovement.isFast == 0){
            labelFast.setText("Slow");
        }

        labelDesc.setText(GamePanel.activeMovement.desc);

        if(!this.isVisible()){
            this.setVisible(true);
        }
    }
}

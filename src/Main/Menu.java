package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

public class Menu extends JFrame{
    private Font pixelFont = null;

    public Menu(){
        setTitle("Chess - The Holy Update");
        setSize(640, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

    // Carregando fonte personalizada
    try {
        InputStream is = getClass().getResourceAsStream("pixel_font.ttf");
    if (is != null) {
        pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f);
    }
    } catch (Exception e) {
        e.printStackTrace();
    }

        // Cor de fundo
        Color background = new Color(52, 49, 69);
        getContentPane().setBackground(background);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Título
        JLabel title = new JLabel("Chess - The Holy Update");
        title.setFont(pixelFont.deriveFont(Font.BOLD, 18f));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        getContentPane().add(Box.createVerticalStrut(30));
        getContentPane().add(title);
        getContentPane().add(Box.createVerticalStrut(30));

        // Botões
        JButton jogarBtn = createButton("Jogar");
        JButton regrasBtn = createButton("Regras");
        JButton sairBtn = createButton("Sair");

        // Ação ao apertar botão "Jogar"
        jogarBtn.addActionListener((ActionEvent e) -> {
            dispose();
            new Main.main();
        });

        // Ação ao apertar botão "Regras"
        regrasBtn.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Regras do jogo ainda não implementadas.", "Regras", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ação ao apertar botão "Sair"
        sairBtn.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        add(jogarBtn);
        add(Box.createVerticalStrut(10));
        add(regrasBtn);
        add(Box.createVerticalStrut(10));
        add(sairBtn);

        setVisible(true);
    }

    // Função para criar botões com design específico
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(pixelFont.deriveFont(Font.PLAIN, 14f));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(76, 72, 100));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        button.setMaximumSize(new Dimension(200, 35));
        button.addMouseListener(new MouseListener(){
            public void mouseEntered(MouseEvent e){
               ((JButton)e.getSource()).setBackground(new Color(0, 0, 0));
            }
            public void mouseExited(MouseEvent e){
                ((JButton)e.getSource()).setBackground(new Color(76, 72, 100));
            }
            public void mouseClicked(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        } );
        return button;
    }

    public static void main(String[] args) {
        new Menu();
    }
}

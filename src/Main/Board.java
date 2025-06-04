package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Board {

    final static int MAX_COL = 8;
    final static int MAX_ROW = 9;

    public static final int SQUARE_SIZE = 16;
    public static final int SPRITE_SIZE = SQUARE_SIZE * 2;
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE / 2;

    public static BufferedImage black, white, bc, wc;

    static int scale = 3;

    public static void draw(Graphics2D g2) throws IOException {
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        g2.transform(at);

        black = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/b_square.png")));
        white = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/w_square.png")));
        bc = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/b_c_square.png")));
        wc = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/w_c_square.png")));

        BufferedImage img = black;

        if(GamePanel.currentColor == GamePanel.WHITE){
            g2.setColor(new Color(129, 137, 179));
        } else {
            g2.setColor(new Color(52, 49, 69));
        }
        g2.fillRect(0, 0, 480, 624);

        for(int row = 0; row < MAX_ROW; row++) {
            if(img == white){
                img = black;
            } else {
                img = white;
            }
            for(int col = 0; col < MAX_COL; col++) {
                if(img == white){
                    img = black;
                } else {
                    img = white;
                }
                if(GamePanel.activePiece != null){
                    if(GamePanel.moveChosen == 0){
                        if(GamePanel.activePiece.preRow == row && GamePanel.activePiece.preCol == col){
                            if(img == white){
                                g2.drawImage(wc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            } else {
                                g2.drawImage(bc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            }
                            continue;
                        }
                    }

                    if(GamePanel.moveChosen == 1){
                        if(GamePanel.activePiece.canMove1(col, row) || (GamePanel.activePiece.preRow == row && GamePanel.activePiece.preCol == col)){
                            if(img == white){
                                g2.drawImage(wc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            } else {
                                g2.drawImage(bc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            }
                            continue;
                        }
                    }

                    if(GamePanel.moveChosen == 2){
                        if(GamePanel.activePiece.canMove2(col, row) || (GamePanel.activePiece.preRow == row && GamePanel.activePiece.preCol == col)){
                            if(img == white){
                                g2.drawImage(wc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            } else {
                                g2.drawImage(bc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            }
                            continue;
                        }
                    }

                    if(GamePanel.moveChosen == 3){
                        if(GamePanel.activePiece.canMove3(col, row) || (GamePanel.activePiece.preRow == row && GamePanel.activePiece.preCol == col)){
                            if(img == white){
                                g2.drawImage(wc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            } else {
                                g2.drawImage(bc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            }
                            continue;
                        }
                    }

                    if(GamePanel.moveChosen == 4){
                        if(GamePanel.activePiece.canMove4(col, row) || (GamePanel.activePiece.preRow == row && GamePanel.activePiece.preCol == col)){
                            if(img == white){
                                g2.drawImage(wc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            } else {
                                g2.drawImage(bc, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
                            }
                            continue;
                        }
                    }
                }
                g2.drawImage(img, col * SQUARE_SIZE + SQUARE_SIZE, row * SQUARE_SIZE + 2*SQUARE_SIZE, null);
            }
        }

    }
}

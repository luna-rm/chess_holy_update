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
    public static BufferedImage divinity, no_divinity, need_divinity;
    public static BufferedImage sin, no_sin, need_sin;
    public static BufferedImage slay, need_slay;

    static int scale = 3;

    public static void draw(Graphics2D g2) throws IOException {
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        g2.transform(at);

        black = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/b_square.png")));
        white = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/w_square.png")));
        bc = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/b_c_square.png")));
        wc = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/w_c_square.png")));

        divinity = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/divinity.png")));
        no_divinity = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/no_divinity.png")));
        need_divinity = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/need_divinity.png")));

        sin = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/sin.png")));
        no_sin = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/no_sin.png")));
        need_sin = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/need_sin.png")));

        slay = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/slay.png")));
        need_slay = ImageIO.read(Objects.requireNonNull(Board.class.getResourceAsStream("./imgs/need_slay.png")));

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

        int div = GamePanel.divinity[GamePanel.BLACK];
        for(int i = 0; i < 10; i++){
            if(div > 0){
                div--;
                g2.drawImage(divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, -SQUARE_SIZE/2, null);
            } else {
                g2.drawImage(no_divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, -SQUARE_SIZE/2, null);
            }
        }

        for(int i = 0; i < GamePanel.reqDivinity[GamePanel.BLACK]; i++){
            g2.drawImage(need_divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, -SQUARE_SIZE/2, null);
        }

        int si = GamePanel.sin[GamePanel.BLACK];
        for(int i = 0; i < 2; i++){
            if(si > 0){
                si--;
                g2.drawImage(sin, i * SQUARE_SIZE + SQUARE_SIZE*7, -SQUARE_SIZE/2, null);
            } else {
                g2.drawImage(no_sin, i * SQUARE_SIZE + SQUARE_SIZE*7, -SQUARE_SIZE/2, null);
            }
        }

        for(int i = 0; i < GamePanel.reqSin[GamePanel.BLACK]; i++){
            g2.drawImage(need_sin, i * SQUARE_SIZE + SQUARE_SIZE*7, -SQUARE_SIZE/2, null);
        }

        for(int i = 0; i < GamePanel.slay[GamePanel.BLACK] && i < 40; i++){
            if(i % 2 == 0){
                g2.drawImage(slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, -4, null);
            } else {
                g2.drawImage(slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, 0, null);
            }
        }

        for(int i = 0; i < GamePanel.reqSlay[GamePanel.BLACK] && i < 40; i++){
            if(i % 2 == 0){
                g2.drawImage(need_slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, -4, null);
            } else {
                g2.drawImage(need_slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, 0, null);
            }
        }

        div = GamePanel.divinity[GamePanel.WHITE];
        for(int i = 0; i < 10; i++){
            if(div > 0){
                div--;
                g2.drawImage(divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*5/4, null);
            } else {
                g2.drawImage(no_divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*5/4, null);
            }
        }

        for(int i = 0; i < GamePanel.reqDivinity[GamePanel.WHITE]; i++){
            g2.drawImage(need_divinity, i * SQUARE_SIZE/2 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*5/4, null);
        }

        si = GamePanel.sin[GamePanel.WHITE];
        for(int i = 0; i < 2; i++){
            if(si > 0){
                si--;
                g2.drawImage(sin, i * SQUARE_SIZE + SQUARE_SIZE*7, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*4/3, null);
            } else {
                g2.drawImage(no_sin, i * SQUARE_SIZE + SQUARE_SIZE*7, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*4/3, null);
            }
        }

        for(int i = 0; i < GamePanel.reqSin[GamePanel.WHITE]; i++){
            g2.drawImage(need_sin, i * SQUARE_SIZE + SQUARE_SIZE*7, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*4/3, null);
        }

        for(int i = 0; i < GamePanel.slay[GamePanel.WHITE] && i < 40; i++){
            if(i % 2 == 0){
                g2.drawImage(slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*3/4 - 4, null);
            } else {
                g2.drawImage(slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*3/4, null);
            }
        }

        for(int i = 0; i < GamePanel.reqSlay[GamePanel.WHITE] && i < 40; i++){
            if(i % 2 == 0){
                g2.drawImage(need_slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*3/4 - 4, null);
            } else {
                g2.drawImage(need_slay, i * SQUARE_SIZE/8 + SQUARE_SIZE, SQUARE_SIZE*MAX_ROW + SQUARE_SIZE*3 - SQUARE_SIZE*3/4, null);
            }
        }
    }
}

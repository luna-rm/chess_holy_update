package Main.Piece;

import Main.Board;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    public BufferedImage image;
    public int x, y;
    public int col, row, preCol, preRow, initCol, initRow;
    public int color;
    public Piece hittingPiece;
    public int id;
    public boolean moved = false;
    public boolean chained = false;
    public int immortal = 0;
    public boolean divineShield = false;

    public Piece(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
        this.initCol = col;
        this.initRow = row;

        x = getX(col);
        y = getY(row);

        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col) {
        return col * Board.SQUARE_SIZE + Board.SQUARE_SIZE;
    }

    public int getY(int row) {
        return row * Board.SQUARE_SIZE + Board.SQUARE_SIZE;
    }

    public int getCol(int x) {
        return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    public int getRow(int y) {
        return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
    }

    public void updatePosition() {
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
        moved = true;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SPRITE_SIZE, null);
        if(this.chained){
            g2.drawImage(getImage("../imgs/chains"), x, y, Board.SQUARE_SIZE, Board.SPRITE_SIZE, null);
        }
        if(this.immortal != 0){
            if(this.id != 11){
                g2.drawImage(getImage("../imgs/immortal"), x, y, Board.SQUARE_SIZE, Board.SPRITE_SIZE, null);
            } else {
                g2.drawImage(getImage("../imgs/immortal_devil"), x, y, Board.SQUARE_SIZE, Board.SPRITE_SIZE, null);
            }
        }
        if(this.divineShield){
            g2.drawImage(getImage("../imgs/divine_shield"), x, y, Board.SQUARE_SIZE, Board.SPRITE_SIZE, null);
        }
    }

    public boolean canMove1(int targetCol, int targetRow) {
        return false;
    }

    public boolean canMove2(int targetCol, int targetRow) {
        return false;
    }

    public boolean canMove3(int targetCol, int targetRow) {
        return false;
    }

    public boolean canMove4(int targetCol, int targetRow) {
        return false;
    }

    public void move1(int x, int y){
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.updatePosition();

        changeTurn(false);
    }

    public void move2(int x, int y){
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.updatePosition();

        changeTurn(false);
    }

    public void move3(int x, int y){
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.updatePosition();

        changeTurn(false);
    }

    public void move4(int x, int y){
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.updatePosition();

        changeTurn(false);
    }

    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >= 0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 8) {
            return true;
        }
        return false;
    }

    public Piece getHittingPiece(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.pieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean isValidSquare(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(hittingPiece == null || hittingPiece.id == 7) {
            return true;
        } else {
            if(hittingPiece.immortal != 0){
                return false;
            }
            if(hittingPiece.color != this.color) {
                return true;
            } else {
                hittingPiece = null;
            }
        }

        return false;
    }

    public int getIndex(){
        for(int index = 0; index < GamePanel.pieces.size(); index++) {
            if(GamePanel.pieces.get(index) == this) {
                return index;
            }
        }
        return -1;
    }

    public boolean isSameSquare(int targetCol, int targetRow) {
        if (targetCol == preCol && targetRow == preRow) {
            return true;
        }
        return false;
    }

    public boolean pieceIsOnStraightLine(int targetCol, int targetRow) {
        for(int c = preCol-1; c > targetCol; c--){
            for(Piece piece : GamePanel.pieces){
                if(piece.col == c && piece.row == targetRow && piece != this) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        for(int c = preCol+1; c < targetCol; c++){
            for(Piece piece : GamePanel.pieces){
                if(piece.col == c && piece.row == targetRow && piece != this) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        for(int r = preRow-1; r > targetRow; r--){
            for(Piece piece : GamePanel.pieces){
                if(piece.col == targetCol && piece.row == r && piece != this) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }

        for(int r = preRow+1; r < targetRow; r++){
            for(Piece piece : GamePanel.pieces){
                if(piece.col == targetCol && piece.row == r && piece != this) {
                    hittingPiece = piece;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pieceIsOnDiagonalLine(int targetCol, int targetRow) {
        if(targetRow < preRow){
            for(int c = preCol-1; c > targetCol; c--){
                int diff = Math.abs(c-preCol);
                for(Piece piece : GamePanel.pieces){
                    if(piece.col == c && piece.row == preRow-diff && piece != this) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            for(int c = preCol+1; c < targetCol; c++){
                int diff = Math.abs(c-preCol);
                for(Piece piece : GamePanel.pieces){
                    if(piece.col == c && piece.row == preRow-diff && piece != this) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }

        if(targetRow > preRow){
            for(int c = preCol-1; c > targetCol; c--){
                int diff = Math.abs(c-preCol);
                for(Piece piece : GamePanel.pieces){
                    if(piece.col == c && piece.row == preRow+diff && piece != this) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }

            for(int c = preCol+1; c < targetCol; c++){
                int diff = Math.abs(c-preCol);
                for(Piece piece : GamePanel.pieces){
                    if(piece.col == c && piece.row == preRow+diff && piece != this) {
                        hittingPiece = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void changeTurn(boolean isFast){
        GamePanel.moveChosen = 0;
        GamePanel.reqDivinity[0] = 0;
        GamePanel.reqDivinity[1] = 0;
        GamePanel.reqSlay[0] = 0;
        GamePanel.reqSlay[1] = 0;
        GamePanel.reqSin[0] = 0;
        GamePanel.reqSin[1] = 0;
        if(GamePanel.horseMove4Aux != 0){
            GamePanel.horseMove4Aux--;
            if(GamePanel.horseMove4Aux == 0){
                GamePanel.activePiece = null;
            }
            return;
        }
        GamePanel.activePiece = null;
        if(isFast){
            if(GamePanel.fast == 0){
                GamePanel.fast++;
                return;
            }
        }

        for(Piece piece : GamePanel.pieces){
            if(piece.color != GamePanel.currentColor){
                if(piece.immortal != 0){
                    piece.immortal--;
                }
                if(piece.chained){
                    piece.chained = false;
                }
            }
        }

        if(GamePanel.two_turns[this.color] == 1){
            GamePanel.fast = 0;
            GamePanel.two_turns[this.color] = 0;
            return;
        }
        if (GamePanel.currentColor == GamePanel.WHITE) {
            GamePanel.currentColor = GamePanel.BLACK;
        } else {
            GamePanel.currentColor = GamePanel.WHITE;
        }
        GamePanel.fast = 0;

        ArrayList<Burn> stop = new ArrayList<>();
        for(Burn burn : GamePanel.burns){
            burn.countdown++;
            if(burn.countdown == 2){
                stop.add(burn);
                burn.move1(0, 0);
            }
        }
        for(Burn burn : stop){
            GamePanel.burns.remove(burn);
        }
    }

    public void holyPower(){
        if(GamePanel.divinity[this.color] < 10){
            GamePanel.divinity[this.color]++;
        }
    }

    public void unholyRitual(){
        if(GamePanel.divinity[this.color] > 0){
            GamePanel.divinity[this.color]--;
        }
    }

    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public void unSelect(){
        GamePanel.moveChosen = 0;
        GamePanel.activePiece = null;
    }

    public boolean reqSlay(int hm){
        GamePanel.reqSlay[this.color] = hm;
        if(GamePanel.slay[this.color] >= hm){
            return true;
        }
        return false;
    }

    public boolean reqSin(int hm){
        GamePanel.reqSin[this.color] = hm;
        if(GamePanel.sin[this.color] >= hm){
            return true;
        }
        return false;
    }

    public boolean reqDiv(int hm){
        GamePanel.reqDivinity[this.color] = hm;
        if(GamePanel.divinity[this.color] >= hm){
            return true;
        }
        return false;
    }

    public void spendSlay(int hm){
        GamePanel.slay[this.color] -= hm;
    }

    public boolean canCast(int color, int sl, int si, int di){
        if(sl >= GamePanel.slay[color]){
            if (si >= GamePanel.sin[color]){
                if(di >= GamePanel.divinity[color]){
                    return true;
                }
            }
        }
        unSelect();
        return false;
    }
}

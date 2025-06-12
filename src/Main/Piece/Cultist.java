package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

import java.util.ArrayList;

public class Cultist extends Piece{
    public Cultist(int color, int col, int row) {
        super(color, col, row);
        this.id = 8;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_cultist");
        } else {
            image = getImage("../imgs/b_cultist");
        }

        movement1 = new Movement("Cultist", 1, 0, 0, 0, 0, 0, "Move 1 in any line or 2 to front");
        movement2 = new Movement("Cultist", 2, 0, 1, 0, 0, 0, "Destroy a piece in any diagonal (require piece to destroy), gain 2 slay");
        movement3 = new Movement("Cultist", 3, 1, 1, 2, 1, 0, "Choose 1 piece, it cannot move until your next round");
        movement4 = new Movement("Cultist", 4, 1, 0, 3, 1, 0, "Explode an ally piece, killing it and all around pieces");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
            int moveValue;
            if(color == GamePanel.WHITE){
                moveValue = -1;
            } else {
                moveValue = 1;
            }

            if(targetCol == preCol && targetRow == preRow+(2*moveValue) && isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                if(hittingPiece != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move2(int x, int y) {
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            gainSlay(3);
        }
        this.updatePosition();

        changeTurn(true);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        if(reqSlay(2) && reqSin(1) || reqSin(1) && reqSlay(2)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (hittingPiece != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move3(int x, int y) {
        if(this.hittingPiece != null) {
            this.hittingPiece.chained = true;
        }

        this.resetPosition();
        spendSlay(2);
        changeTurn(true);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqSlay(3) && reqSin(1) || reqSin(1) && reqSlay(3)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow)) {
                if (hittingPiece != null && hittingPiece.color == this.color) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move4(int x, int y) {
        ArrayList<Piece> kill = new ArrayList<>();
        GamePanel.pieces.remove(this.hittingPiece);
        gainSlay(1);
        for(Piece piece : GamePanel.pieces) {
            if(Math.abs(this.hittingPiece.preCol - piece.col) + Math.abs(this.hittingPiece.preRow - piece.row) == 1 || (this.preCol == piece.col && this.preRow == piece.row)) {
                if(piece.immortal == 0 && piece != this){
                    kill.add(piece);
                }
            }
        }
        for(Piece piece : kill) {
            gainSlay(1);
            GamePanel.pieces.remove(piece);
        }

        this.resetPosition();
        spendSlay(3);
        changeTurn(false);
    }
}

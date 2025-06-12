package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        this.id = 0;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_pawn");
        } else {
            image = getImage("../imgs/b_pawn");
        }

        movement1 = new Movement("Pawn", 1, 0, 0, 0, 0, 0, "Move 1 (or 2 if it is the first movement) to front, without destroying");
        movement2 = new Movement("Pawn", 2, 0, 0, 0, 0, 0, "Destroy a enemy piece in any front diagonal");
        movement3 = new Movement("Pawn", 3, 1, 1, 0, 0, 0, "Kill a pawn to transform me into a Cultist");
        movement4 = new Movement("Pawn", 4, 2, 0, 0, 0, 2, "Move 1 front and transform into a Paladin");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            int moveValue;
            if(color == GamePanel.WHITE){
                moveValue = -1;
            } else {
                moveValue = 1;
            }

            hittingPiece = getHittingPiece(targetCol, targetRow);

            if(targetCol == preCol && targetRow == preRow+moveValue && hittingPiece == null){
                return true;
            }
            if(!moved){
                if(targetCol == preCol && targetRow == preRow+(2*moveValue) && hittingPiece == null && !pieceIsOnStraightLine(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        int moveValue;
        if(color == GamePanel.WHITE){
            moveValue = -1;
        } else {
            moveValue = 1;
        }

        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingPiece.color != color && hittingPiece.immortal == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && hittingPiece.color == color && hittingPiece != this && hittingPiece.id == 0) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y){
        unholyRitual();
        if(this.hittingPiece != null && this.hittingPiece.id == 0) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            gainSlay(1);
        }
        this.resetPosition();
        Cultist c = new Cultist(this.color, this.col, this.row);
        c.initCol = this.initCol;
        c.initRow = this.initRow;
        GamePanel.pieces.add(c);
        GamePanel.pieces.remove(this);

        changeTurn(true);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqDiv(2)) {
            int moveValue;
            if (color == GamePanel.WHITE) {
                moveValue = -1;
            } else {
                moveValue = 1;
            }

            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (targetCol == preCol && targetRow == preRow + moveValue && hittingPiece == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move4(int x, int y){
        holyPower();
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            gainSlay(1);
        }
        this.updatePosition();
        Paladin p = new Paladin(this.color, this.col, this.row);
        p.initCol = this.initCol;
        p.initRow = this.initRow;
        GamePanel.pieces.add(p);
        GamePanel.pieces.remove(this);

        changeTurn(false);
    }
}

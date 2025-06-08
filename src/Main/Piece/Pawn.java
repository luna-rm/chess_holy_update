package Main.Piece;

import Main.Board;
import Main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        this.id = 0;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_pawn");
        } else {
            image = getImage("../imgs/b_pawn");
        }
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
            if(hittingPiece != null && Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hittingPiece.color != color) {
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
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.resetPosition();
        GamePanel.pieces.add(new Cultist(this.color, this.col, this.row));
        GamePanel.pieces.remove(this.hittingPiece.getIndex());

        changeTurn(true);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        int moveValue;
        if(color == GamePanel.WHITE){
            moveValue = -1;
        } else {
            moveValue = 1;
        }

        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(targetCol == preCol && targetRow == preRow+moveValue && hittingPiece == null){
                return true;
            }
        }
        return false;
    }

    public void move4(int x, int y){
        if(GamePanel.divinity[this.color] >= 3) {
            holyPower();
            this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
            this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
            if(this.hittingPiece != null) {
                GamePanel.pieces.remove(this.hittingPiece.getIndex());
                GamePanel.slay[GamePanel.currentColor]++;
            }
            this.updatePosition();
            GamePanel.pieces.add(new Paladin(this.color, this.col, this.row));
            GamePanel.pieces.remove(this.hittingPiece.getIndex());

            changeTurn(false);
        }
    }
}

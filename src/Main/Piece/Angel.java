package Main.Piece;

import Main.GamePanel;
import Main.Movement;

public class Angel extends Piece {
    public Angel(int color, int col, int row)  {
        super(color, col, row);
        this.id = 10;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_angel");
        } else {
            image = getImage("../imgs/b_angel");
        }

        movement1 = new Movement("Angel", 1, 2, 1, 0, 0, 0, "Give allied pieces in line Immortal 1");
        movement2 = new Movement("Angel", 2, 0, 0, 0, 0, 0, "Move to any place on the board without killing");
        movement3 = new Movement(true);
        movement4 = new Movement(true);
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(hittingPiece != null && hittingPiece.color == this.color) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move1(int x, int y) {
        holyPower();
        for(Piece piece : GamePanel.pieces) {
            if(Math.abs(piece.col - preCol) + Math.abs(piece.row - preRow) == 1) {
                if(piece != this){
                    piece.immortal++;
                }
            }
        }
        this.resetPosition();
        changeTurn(true);
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece == null) {
                return true;
            }
        }
        return false;
    }
}

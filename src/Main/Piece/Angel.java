package Main.Piece;

import Main.GamePanel;

public class Angel extends Piece {
    public Angel(int color, int col, int row)  {
        super(color, col, row);
        this.id = 10;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_bis");
        } else {
            image = getImage("../imgs/b_bis");
        }
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

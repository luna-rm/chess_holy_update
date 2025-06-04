package Main.Piece;

import Main.GamePanel;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_pawn");
        } else {
            image = getImage("../imgs/b_pawn");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
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
                if(targetCol == preCol && targetRow == preRow+(2*moveValue) && hittingPiece == null){
                    return true;
                }
            }
        }
        return false;
    }
}

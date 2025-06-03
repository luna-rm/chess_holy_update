package Main.Piece;

import Main.GamePanel;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_king");
        } else {
            image = getImage("../imgs/b_king");
        }
    }

    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                return true;
            }
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                return true;
            }
        }

        return false;
    }
}

package Main.Piece;

import Main.GamePanel;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        this.id = 5;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_king");
        } else {
            image = getImage("../imgs/b_king");
        }
    }

    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }

        return false;
    }
}

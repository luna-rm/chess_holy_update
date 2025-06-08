package Main.Piece;

import Main.GamePanel;

public class Cultist extends Piece{
    public Cultist(int color, int col, int row) {
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
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}

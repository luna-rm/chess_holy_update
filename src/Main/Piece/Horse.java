package Main.Piece;

import Main.GamePanel;

public class Horse extends Piece {
    public Horse(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_horse");
        } else {
            image = getImage("../imgs/b_horse");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol-preCol) * Math.abs(targetRow-preRow) == 2){
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}

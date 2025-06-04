package Main.Piece;

import Main.GamePanel;

public class Bis extends Piece {
    public Bis(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_bis");
        } else {
            image = getImage("../imgs/b_bis");
        }
    }

    @Override
    public boolean canMove(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}

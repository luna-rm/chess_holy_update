package Main.Piece;

import Main.GamePanel;

public class Queen extends Piece {

    public Queen(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_queen");
        } else {
            image = getImage("../imgs/b_queen");
        }
    }
}

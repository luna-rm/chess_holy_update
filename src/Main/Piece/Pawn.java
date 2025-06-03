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
}

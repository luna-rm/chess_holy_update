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
}

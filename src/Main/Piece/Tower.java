package Main.Piece;

import Main.GamePanel;

public class Tower extends Piece {
    public Tower(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_tower");
        } else {
            image = getImage("../imgs/b_tower");
        }
    }
}

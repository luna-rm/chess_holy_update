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
}

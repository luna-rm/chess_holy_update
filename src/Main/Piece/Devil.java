package Main.Piece;

import Main.GamePanel;

public class Devil extends Piece {
    public Devil(int color, int col, int row)  {
        super(color, col, row);
        this.id = 11;
        this.immortal = 1000;
        this.divineShield = true;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_bis");
        } else {
            image = getImage("../imgs/b_bis");
        }
    }

}

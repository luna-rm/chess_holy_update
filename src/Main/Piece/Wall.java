package Main.Piece;

import Main.GamePanel;
import Main.Movement;

public class Wall extends Piece {
    public Wall(int color, int col, int row) {
        super(color, col, row);
        this.id = 6;

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_wall");
        } else {
            image = getImage("../imgs/b_wall");
        }

        movement1 = new Movement("Wall", 1, 0, 1, 0, 0, 0, "Destroy self, doesn’t count as slay");
        movement2 = new Movement(true);
        movement3 = new Movement(true);
        movement4 = new Movement(true);
    }

    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && targetCol == this.preCol && targetRow == this.preRow) {
            return true;
        }
        return false;
    }

    public void move1(int x, int y){
        GamePanel.pieces.remove(this);
        changeTurn(true);
    }
}

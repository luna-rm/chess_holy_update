package Main.Piece;

import Main.GamePanel;

import java.util.ArrayList;

public class Burn extends Piece {
    int countdown = 0;

    public Burn(int color, int col, int row) {
        super(color, col, row);
        this.id = 7;

        image = getImage("../imgs/burn");
    }

    public void move1(int x, int y){
        ArrayList<Piece> kill = new ArrayList<>();
        GamePanel.pieces.remove(this);
        for(Piece piece : GamePanel.pieces) {
            if(Math.abs(this.preCol - piece.col) + Math.abs(this.preRow - piece.row) == 1 || (this.preCol == piece.col && this.preRow == piece.row)) {
                if(piece.immortal == 0){
                    kill.add(piece);
                }
            }
        }
        for(Piece piece : kill) {
            GamePanel.slay[this.color]++;
            GamePanel.pieces.remove(piece);
        }
    }
}

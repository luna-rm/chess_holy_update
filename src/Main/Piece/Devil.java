package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

import java.util.Collections;

public class Devil extends Piece {
    public Devil(int color, int col, int row)  {
        super(color, col, row);
        this.id = 11;
        this.immortal = 1;
        this.divineShield = true;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_devil");
        } else {
            image = getImage("../imgs/b_devil");
        }

        movement1 = new Movement("Devil", 1, 1, 0, 3, 0, 0, "Move 3 to any direction, if I kill I gain Immortal 1; if you can't pay the price random allied pieces will");
        movement2 = new Movement("Devil", 2, 0, 0, 0, 0, 0, "Kill me");
        movement3 = new Movement(true);
        movement4 = new Movement(true);
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        int sacrifices = 0;
        for(Piece piece : GamePanel.pieces){
            if(piece.color == this.color && piece != this){
                sacrifices++;
            }
        }

        if(reqSlay(3) || sacrifices >= 3){
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)) {
                    if(Math.abs(targetCol-preCol) <= 3) {
                        if (!pieceIsOnDiagonalLine(targetCol, targetRow)) {
                            if(hittingPiece != null && hittingPiece.immortal != 0) {
                                return false;
                            }
                            return true;
                        }
                    }
                }
                if (targetCol == preCol || targetRow == preRow) {
                    if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) <= 3) {
                        if (!pieceIsOnStraightLine(targetCol, targetRow)) {
                            if(hittingPiece != null && hittingPiece.immortal != 0) {
                                return false;
                            }
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void move1(int x, int y) {
        unholyRitual();
        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            this.immortal++;
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            gainSlay(1);
        }
        int need_sacrifice = 3;
        for(int i = 0; i < need_sacrifice; i++){
            if(GamePanel.slay[this.color] > 0){
                spendSlay(1);
            } else {
                Piece sac = null;
                Collections.shuffle(GamePanel.pieces);
                for(Piece piece : GamePanel.pieces){
                    if(piece.color == this.color && piece != this){
                        sac = piece;
                    }
                }
                if(sac != null){
                    GamePanel.pieces.remove(sac.getIndex());
                }
            }
        }

        this.updatePosition();
        changeTurn(false);
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow)) {
            return true;
        }
        return false;
    }

    public void move2(int x, int y){
        GamePanel.pieces.remove(this);
        changeTurn(false);
    }
}

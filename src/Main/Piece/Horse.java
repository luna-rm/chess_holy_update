package Main.Piece;

import Main.GamePanel;
import Main.Movement;

public class Horse extends Piece {
    public Horse(int color, int col, int row) {
        super(color, col, row);
        this.id = 2;

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_horse");
        } else {
            image = getImage("../imgs/b_horse");
        }

        movement1 = new Movement("Horse", 1, 0, 0, 0, 0, 0, "Jump in L");
        movement2 = new Movement("Horse", 2, 0, 1, 0, 0, 0, "Destroy piece in back 1 without moving");
        movement3 = new Movement("Horse", 3, 1, 1, 4, 0, 0, "Transform a piece in a horse");
        movement4 = new Movement("Horse", 4, 1, 1, 6, 1, 0, "Play with a enemy piece, pass divine shield");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol-preCol) * Math.abs(targetRow-preRow) == 2){
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        int moveValue;
        if(color == GamePanel.WHITE){
            moveValue = 1;
        } else {
            moveValue = -1;
        }

        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && Math.abs(targetCol - preCol) == 0 && targetRow == preRow + moveValue) {
                if(hittingPiece.immortal == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void move2(int x, int y){
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            gainSlay(1);
        }
        this.resetPosition();

        changeTurn(true);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        if(reqSlay(4)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (hittingPiece != null && !hittingPiece.divineShield) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move3(int x, int y){
        spendSlay(4);
        Horse horse = new Horse(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        horse.initCol = this.hittingPiece.initCol;
        horse.initRow = this.hittingPiece.initRow;
        horse.immortal = hittingPiece.immortal;
        GamePanel.pieces.add(horse);
        GamePanel.pieces.remove(this.hittingPiece.getIndex());

        this.resetPosition();
        changeTurn(false);

    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqSlay(6) && reqSin(1) || reqSin(1) && reqSlay(6)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                int enemy = GamePanel.WHITE;
                if (this.color == GamePanel.WHITE) {
                    enemy = GamePanel.BLACK;
                }
                if (hittingPiece != null && hittingPiece.color == enemy && !hittingPiece.chained) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move4(int x, int y){
        spendSlay(6);
        this.resetPosition();

        GamePanel.horseMove4Aux = 2;
        GamePanel.activePiece = this.hittingPiece;

        changeTurn(true);
    }
}

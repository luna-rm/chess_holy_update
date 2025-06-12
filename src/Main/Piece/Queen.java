package Main.Piece;

import Main.GamePanel;
import Main.Movement;

public class Queen extends Piece {
    public Queen(int color, int col, int row) {
        super(color, col, row);
        divineShield = true;
        this.id = 4;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_queen");
        } else {
            image = getImage("../imgs/b_queen");
        }

        movement1 = new Movement("Queen", 1, 0, 0, 0, 0, 0, "Move X to any direction");
        movement2 = new Movement("Queen", 2, 0, 1, 0, 0, 0, "Next turn, you gain double slay");
        movement3 = new Movement("Queen", 3, 1, 0, 5, 2, 0, "Sacrifice me to have another turn");
        movement4 = new Movement("Queen", 4, 2, 0, 0, 0, 9, "Lose all Divinity, back all pieces to it’s started position");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)) {
                    return true;
                }
            }
            if (targetCol == preCol || targetRow == preRow) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow)) {
            return true;
        }
        return false;
    }

    @Override
    public void move2(int x, int y) {
        this.resetPosition();
        GamePanel.queenMove2Aux[this.color] = 2;
        changeTurn(true);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        if(reqSlay(1) && reqSin(2) || reqSin(2) && reqSlay(1)) {
            if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow)) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y){
        spendSlay(1);
        unholyRitual();
        gainSlay(1);
        GamePanel.pieces.remove(this);
        GamePanel.two_turns[this.color] = 1;
        changeTurn(true);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqDiv(9)) {
            if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow)) {
                return true;
            }
        }
        return false;
    }

    public void move4(int x, int y){
        holyPower();
        GamePanel.divinity[this.color] = 0;

        for (Piece piece : GamePanel.pieces) {
            piece.col = piece.initCol;
            piece.row = piece.initRow;
            piece.updatePosition();
        }

        changeTurn(false);
    }

}

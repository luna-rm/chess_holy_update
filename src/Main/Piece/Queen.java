package Main.Piece;

import Main.GamePanel;

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
    public boolean canMove3(int targetCol, int targetRow) {
        if(reqSlay(1) && reqSin(2)) {
            if (isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow)) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y){
        Piece king = null;
        for (Piece piece : GamePanel.pieces) {
            if (piece.id == 5 && piece.color == this.color) {
                king = piece;
            }
        }

        if (king == null) {
            unSelect();
            return;
        }

        boolean isAble = false;
        for (Piece piece : GamePanel.pieces) {
            if (Math.abs(king.preCol - piece.col) + Math.abs(king.preRow - piece.row) == 1 || Math.abs(king.preCol - piece.col) * Math.abs(king.preRow - piece.row) == 1) {
                isAble = true;
            }

        }

        if (isAble) {
            spendSlay(1);
            unholyRitual();
            GamePanel.slay[this.color] += 1;
            GamePanel.pieces.remove(this);
            GamePanel.two_turns[this.color] = 1;
            changeTurn(true);

        }
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

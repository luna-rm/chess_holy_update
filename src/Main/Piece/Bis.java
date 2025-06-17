package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

public class Bis extends Piece {
    public Bis(int color, int col, int row) {
        super(color, col, row);
        this.id = 3;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_bis");
        } else {
            image = getImage("../imgs/b_bis");
        }

        movement1 = new Movement("Bishop", 1, 0, 0, 0, 0, 0, "Move X in diagonal");
        movement2 = new Movement("Bishop", 2, 0, 1, 0, 0, 0, "A non-Bishop piece move back 1 (without kill) and it gains Immortal 1");
        movement3 = new Movement("Bishop", 3, 2, 1, 0, 0, 0, "Transform a piece in a Bishop and give it Immortal 1");
        movement4 = new Movement("Bishop", 4, 2, 0, 0, 0, 10, "Place an Angel on the field");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (Math.abs(targetCol-preCol) == Math.abs(targetRow-preRow)) {
                if (isValidSquare(targetCol, targetRow) && !pieceIsOnDiagonalLine(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && hittingPiece.color == this.color && hittingPiece.id != 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void move2(int x, int y) {
        this.resetPosition();
        int moveValue;
        if (color == GamePanel.WHITE) {
            moveValue = 1;
        } else {
            moveValue = -1;
        }

        boolean havePiece = false;
        for(Piece piece : GamePanel.pieces) {
            if (piece.col == this.hittingPiece.preCol && piece.row == this.hittingPiece.row + moveValue) {
                havePiece = true;
            }
        }

        if(!havePiece) {
            this.hittingPiece.col = this.hittingPiece.col;
            this.hittingPiece.row = this.hittingPiece.row + moveValue;
            this.hittingPiece.updatePosition();
        }

        this.hittingPiece.immortal++;
        changeTurn(true);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && hittingPiece.color == this.color) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y){
        holyPower();
        Bis bis = new Bis(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        bis.initCol = this.hittingPiece.initCol;
        bis.initRow = this.hittingPiece.initRow;
        bis.immortal = hittingPiece.immortal;
        bis.immortal++;
        GamePanel.pieces.add(bis);
        GamePanel.pieces.remove(this.hittingPiece.getIndex());

        this.resetPosition();
        changeTurn(true);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqDiv(10)){
            int angels = 0;
            for(Piece p : GamePanel.pieces){
                if(p.id == 10 && p.color == this.color){
                    angels++;
                }
            }

            if(angels != 0){
                return false;
            }

            hittingPiece = getHittingPiece(targetCol, targetRow);

            if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if(hittingPiece == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move4(int x, int y){
        GamePanel.pieces.add(new Angel(this.color, (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE), (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE)));

        this.resetPosition();
        changeTurn(false);
    }
}

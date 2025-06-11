package Main.Piece;

import Main.Board;
import Main.GamePanel;

public class Bis extends Piece {
    public Bis(int color, int col, int row) {
        super(color, col, row);
        this.id = 3;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_bis");
        } else {
            image = getImage("../imgs/b_bis");
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
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(hittingPiece == null) {
                    return true;
                }
            }
        }
        return false;
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
        bis.immortal = hittingPiece.immortal;
        bis.immortal++;
        GamePanel.pieces.add(bis);
        GamePanel.pieces.remove(this.hittingPiece.getIndex());

        this.resetPosition();
        changeTurn(false);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqDiv(10)){
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

package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

import java.util.ArrayList;

public class Paladin extends Piece {
    public Paladin(int color, int col, int row) {
        super(color, col, row);
        divineShield = true;
        this.id = 9;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_paladin");
        } else {
            image = getImage("../imgs/b_paladin");
        }

        movement1 = new Movement("Paladin", 1, 0, 0, 0, 0, 0, "Move 1 to any direction");
        movement2 = new Movement("Paladin", 2, 0, 0, 0, 0, 0, "I and another Paladin move 1 front");
        movement3 = new Movement("Paladin", 3, 2, 0, 0, 0, 0, "Give your paladins Immortal 1, if Divinity 7: Fast");
        movement4 = new Movement("Paladin", 4, 2, 1, 0, 0, 10, "Change Place with the Angel");
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
                if(isValidSquare(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (hittingPiece != null && hittingPiece.color == this.color && hittingPiece.id == 9) {
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
            moveValue = -1;
        } else {
            moveValue = 1;
        }

        boolean havePiece = false;
        Piece enemyPiece = null;
        boolean havePieceHitting = false;
        Piece enemyPieceHitting = null;
        for(Piece piece : GamePanel.pieces) {
            if (piece.color == this.color && piece.col == this.preCol && piece.row == this.preRow + moveValue) {
                havePiece = true;
            }

            if (piece.color != this.color && piece.col == this.preCol && piece.row == this.preRow + moveValue) {
                enemyPiece = piece;
            }

            if (piece.color == this.hittingPiece.color && piece.col == this.hittingPiece.preCol && piece.row == this.hittingPiece.row + moveValue) {
                havePieceHitting = true;
            }

            if (piece.color != this.hittingPiece.color && piece.col == this.hittingPiece.preCol && piece.row == this.hittingPiece.row + moveValue) {
                enemyPieceHitting = piece;
            }
        }

        if(!havePiece) {
            this.col = this.col;
            this.row = this.row + moveValue;

            if(enemyPiece != null){
                GamePanel.pieces.remove(enemyPiece);
                gainSlay(1);
            }
        }

        if(!havePieceHitting) {
            this.hittingPiece.col = this.hittingPiece.col;
            this.hittingPiece.row = this.hittingPiece.row + moveValue;
            this.hittingPiece.updatePosition();
            if(enemyPieceHitting != null){
                GamePanel.pieces.remove(enemyPieceHitting);
                gainSlay(1);
            }
        }

        this.updatePosition();

        changeTurn(false);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if (isWithinBoard(targetCol, targetRow)) {
            if (hittingPiece != null && hittingPiece.color == this.color && hittingPiece.id == 9) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void move3(int x, int y) {
        for(Piece piece : GamePanel.pieces) {
            if(piece.color == this.color && piece.id == 9){
                piece.immortal++;
            }
        }

        this.resetPosition();
        if(reqDiv(7)){
            holyPower();
            changeTurn(true);
            return;
        }
        holyPower();
        changeTurn(false);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        if(reqDiv(10)){
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow)) {
                if (hittingPiece != null && hittingPiece.color == this.color && hittingPiece.id == 10) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void move4(int x, int y) {
        holyPower();
        this.resetPosition();
        this.row = this.hittingPiece.row;
        this.col = this.hittingPiece.col;
        this.hittingPiece.row = this.preRow;
        this.hittingPiece.col = this.preCol;
        this.updatePosition();
        this.hittingPiece.updatePosition();
        changeTurn(true);
    }
}

package Main.Piece;

import Main.Board;
import Main.GamePanel;
import Main.Movement;

import java.util.ArrayList;
import java.util.Collections;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        divineShield = true;
        this.id = 5;
        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_king");
        } else {
            image = getImage("../imgs/b_king");
        }

        movement1 = new Movement("King", 1, 0, 0, 0, 0, 0, "Move 1 to any direction");
        movement2 = new Movement("King", 2, 0, 1, 0, 0, 0, "Transform an allied Pawn in the enemy side to a random base non-King piece");
        movement3 = new Movement("King", 3, 1, 0, 3, 2, 0, "Sacrifice 3 Cultists to transform me into the Devil");
        movement4 = new Movement("King", 4, 2, 1, 2, 0, 0, "Place an Pawn on your side of the field");
    }

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

    public boolean canMove2(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (hittingPiece != null && hittingPiece.id == 0 && hittingPiece.color == this.color) {
                if(this.color == GamePanel.WHITE) {
                    if(hittingPiece.row <= 2){
                        return true;
                    }
                } else {
                    if(hittingPiece.row >= 6){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void move2(int x, int y) {
        GamePanel.pieces.remove(this.hittingPiece);
        Piece p;
        int randomNum = (int)(Math.random() * 4) + 1;
        if(randomNum == 1){
            p = new Tower(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        } else if(randomNum == 2){
            p = new Horse(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        } else if(randomNum == 3){
            p = new Bis(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        } else {
            p = new Queen(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row);
        }

        GamePanel.pieces.add(p);

        this.resetPosition();
        changeTurn(true);
    }

    public boolean canMove3(int targetCol, int targetRow) {
        if(reqSlay(3) && reqSin(2) || reqSin(2) && reqSlay(4)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (hittingPiece != null && hittingPiece.id == 8 && hittingPiece.color == this.color) {
                    int numCultists = 0;
                    for (Piece piece : GamePanel.pieces) {
                        if (piece.color == this.color) {
                            if (piece.id == 8) {
                                numCultists++;
                            }
                        }
                    }
                    if (numCultists >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void move3(int x, int y) {
        unholyRitual();
        this.resetPosition();
        spendSlay(3);
        Collections.shuffle(GamePanel.pieces);
        ArrayList<Piece> kill = new ArrayList<Piece>();
        for(Piece piece : GamePanel.pieces) {
            if(kill.size() == 3){
                break;
            }
            if(piece.id == 8){
                kill.add(piece);
            }
        }
        for(Piece piece : kill){
            GamePanel.pieces.remove(piece);
            gainSlay(1);
        }

        GamePanel.pieces.remove(this);
        Devil d = new Devil(this.color, this.col, this.row);
        d.initCol = this.initCol;
        d.initRow = this.initRow;
        GamePanel.pieces.add(d);

        this.resetPosition();
        changeTurn(false);
    }

    public boolean canMove4(int targetCol, int targetRow) {
        if(reqSlay(2)) {
            hittingPiece = getHittingPiece(targetCol, targetRow);

            if (isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
                if (hittingPiece == null) {
                    if (this.color == GamePanel.BLACK) {
                        if (targetRow <= 2) {
                            return true;
                        }
                    } else {
                        if (targetRow >= 6) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void move4(int x, int y) {
        holyPower();
        spendSlay(2);

        GamePanel.pieces.add(new Pawn(this.color, (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE), (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE)));

        this.resetPosition();
        changeTurn(true);
    }
}

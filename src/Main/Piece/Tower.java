package Main.Piece;

import Main.Board;
import Main.GamePanel;

public class Tower extends Piece {
    public Tower(int color, int col, int row) {
        super(color, col, row);
        this.id = 1;

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_tower");
        } else {
            image = getImage("../imgs/b_tower");
        }
    }

    @Override
    public boolean canMove1(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
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
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if (targetCol == preCol || targetRow == preRow) {
                if (hittingPiece != null && !pieceIsOnStraightLine(targetCol, targetRow)) {
                    try{
                        if(hittingPiece.id == 5 && hittingPiece.color == this.color){
                            return true;
                        }
                    }
                    catch(Exception e){
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public void move2(int x, int y) {
        if(this.preCol < this.hittingPiece.col){
            this.hittingPiece.col--;
        }
        if(this.preCol > this.hittingPiece.col){
            this.hittingPiece.col++;
        }
        if(this.preRow < this.hittingPiece.row){
            this.hittingPiece.row--;
        }
        if(this.preRow > this.hittingPiece.row){
            this.hittingPiece.row++;
        }

        this.hittingPiece.updatePosition();

        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);

        this.updatePosition();
        changeTurn(false);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 2) {
                return true;
            }
            if(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y) {
        unholyRitual();
        GamePanel.pieces.add(new Burn(this.color, (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE), (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE)));
        GamePanel.burns.add(new Burn(this.color, (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE), (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE)));
        this.resetPosition();
        changeTurn(false);
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        int moveValue;
        if(color == GamePanel.WHITE){
            moveValue = 1;
        } else {
            moveValue = -1;
        }

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(Math.abs(targetCol - preCol) == 0 && targetRow == preRow + moveValue && isValidSquare(targetCol, targetCol)) {
                return true;
            }
        }
        return false;
    }

    public void move4(int x, int y) {
        holyPower();
        GamePanel.pieces.add(new Wall(this.color, this.preCol, this.preRow));

        this.col = (x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        this.row = (y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.updatePosition();

        changeTurn(false);
    }
}

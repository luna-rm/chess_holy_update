package Main.Piece;

import Main.GamePanel;

public class Horse extends Piece {
    public Horse(int color, int col, int row) {
        super(color, col, row);
        this.id = 2;

        if(color == GamePanel.WHITE){
            image = getImage("../imgs/w_horse");
        } else {
            image = getImage("../imgs/b_horse");
        }
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
                return true;
            }
        }
        return false;
    }

    public void move2(int x, int y){
        if(this.hittingPiece != null) {
            GamePanel.pieces.remove(this.hittingPiece.getIndex());
            GamePanel.slay[GamePanel.currentColor]++;
        }
        this.resetPosition();

        changeTurn(false);
    }

    @Override
    public boolean canMove3(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            if(hittingPiece != null && hittingPiece.divineShield == false) {
                return true;
            }
        }
        return false;
    }

    public void move3(int x, int y){
        if(GamePanel.slay[this.color] >= 4){
            GamePanel.slay[this.color] -= 4;
            GamePanel.pieces.add(new Horse(this.hittingPiece.color, this.hittingPiece.col, this.hittingPiece.row));
            GamePanel.pieces.remove(this.hittingPiece.getIndex());

            this.resetPosition();
            changeTurn(false);
        }
    }

    @Override
    public boolean canMove4(int targetCol, int targetRow) {
        hittingPiece = getHittingPiece(targetCol, targetRow);

        if(isWithinBoard(targetCol, targetRow) && !isSameSquare(targetCol, targetRow)) {
            int enemy = GamePanel.WHITE;
            if(this.color == GamePanel.WHITE){
                enemy = GamePanel.BLACK;
            }
            if(hittingPiece != null && hittingPiece.color == enemy) {
                return true;
            }
        }
        return false;
    }

    public void move4(int x, int y){
        if(GamePanel.slay[this.color] >= 6 && GamePanel.sin[this.color] >= 0){
            GamePanel.slay[this.color] -= 6;
            this.resetPosition();

            GamePanel.horseMove4Aux = 2;
            GamePanel.activePiece = this.hittingPiece;

            GamePanel.two_turns[this.color] = 1;
            changeTurn(false);
        }
    }
}

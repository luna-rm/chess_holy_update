package Main;

import Main.Piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class PieceComparator implements Comparator<Piece> {
    public int compare(Piece p1, Piece p2) {
        if(p1.row == p2.row && p1.id == 7){
            return -1;
        }

        if(p1.row == p2.row && p2.id == 7){
            return 1;
        }

        return Integer.compare(p1.row, p2.row);
    }
}

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 624;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    public static ArrayList<Burn> burns = new ArrayList<>();
    public static Piece activePiece;

    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public static int gameover = -1;

    public static int currentColor = WHITE;
    int not_released = 1;
    public static int moveChosen = 0;

    boolean canMove;
    boolean validSquare;

    public static int[] slay = new int[]{0, 0};
    public static int[] sin = new int[]{0, 0};
    public static int[] divinity = new int[]{0, 0};

    public static int[] reqSlay = new int[]{0, 0};
    public static int[] reqSin = new int[]{0, 0};
    public static int[] reqDivinity = new int[]{0, 0};

    public static int horseMove4Aux = 0;
    public static int[] two_turns = new int[]{0, 0};
    public static int[] queenMove2Aux = new int[]{0, 0};

    public static int fast = 0;

    public static Movement activeMovement = new Movement();
    private void update(){
        if(mouse.pressed && not_released == 1){
            not_released = 0;
            if(activePiece == null) {
                for (Piece p : pieces) {
                    if(horseMove4Aux == 0){
                        if (p.color == currentColor && p.col == (mouse.x/3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE) && p.row == (mouse.y/3 - Board.SQUARE_SIZE*2) / (Board.SQUARE_SIZE) && !p.chained) {
                            activePiece = p;
                        }
                    }
                }
            } else if(moveChosen != 0){
                if(validSquare) {
                    if(moveChosen == 1){
                        activePiece.move1(mouse.x, mouse.y);
                    } else if(moveChosen == 2){
                        activePiece.move2(mouse.x, mouse.y);
                    }else if(moveChosen == 3){
                        activePiece.move3(mouse.x, mouse.y);
                    }else if(moveChosen == 4){
                        activePiece.move4(mouse.x, mouse.y);
                    }
                    Collections.sort(pieces, new PieceComparator());

                    int[] howManyBis = new int[]{0, 0};
                    for(Piece p : pieces){
                        if(p.id == 3){
                            howManyBis[p.color]++;
                        }
                    }
                    sin[WHITE] = 2 - howManyBis[WHITE];
                    if(sin[WHITE] < 0){
                        sin[WHITE] = 0;
                    }

                    sin[BLACK] = 2 - howManyBis[BLACK];
                    if(sin[BLACK] < 0){
                        sin[BLACK] = 0;
                    }
                }
            }
        }

        if(mouse.right && horseMove4Aux == 0){
            not_released = 1;

            GamePanel.moveChosen = 0;
            GamePanel.reqDivinity[0] = 0;
            GamePanel.reqDivinity[1] = 0;
            GamePanel.reqSlay[0] = 0;
            GamePanel.reqSlay[1] = 0;
            GamePanel.reqSin[0] = 0;
            GamePanel.reqSin[1] = 0;

            if(activePiece != null) {
                activePiece.resetPosition();
            }
            activePiece = null;
        }

        if(!mouse.pressed && not_released == 0) {
            not_released = 1;
        }

        if(activePiece != null){
            simulate();
        }
    }

    private void simulate() {
        activePiece.x = (mouse.x/3) / (Board.SQUARE_SIZE) * (Board.SQUARE_SIZE);
        activePiece.y = (mouse.y/3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE) * (Board.SQUARE_SIZE);
        activePiece.col = (mouse.x / 3 - Board.SQUARE_SIZE) / (Board.SQUARE_SIZE);
        activePiece.row = (mouse.y / 3 - Board.SQUARE_SIZE * 2) / (Board.SQUARE_SIZE);

        if(moveChosen == 1){
            if(activeMovement != activePiece.movement1){
                activeMovement = activePiece.movement1;
                main.info.reload();
            }
        }

        if(moveChosen == 2){
            if(activeMovement != activePiece.movement2){
                activeMovement = activePiece.movement2;
                main.info.reload();
            }
        }

        if(moveChosen == 3){
            if(activeMovement != activePiece.movement3){
                activeMovement = activePiece.movement3;
                main.info.reload();
            }
        }

        if(moveChosen == 4){
            if(activeMovement != activePiece.movement4){
                activeMovement = activePiece.movement4;
                main.info.reload();
            }
        }

        if(moveChosen == 1 && activePiece.canMove1(activePiece.col, activePiece.row)){
            canMove = true;
            validSquare = true;
        } else if (moveChosen == 2 && activePiece.canMove2(activePiece.col, activePiece.row)){
            canMove = true;
            validSquare = true;
        } else if (moveChosen == 3 && activePiece.canMove3(activePiece.col, activePiece.row)){
            canMove = true;
            validSquare = true;
        } else if (moveChosen == 4 && activePiece.canMove4(activePiece.col, activePiece.row)){
            canMove = true;
            validSquare = true;
        } else {
            canMove = false;
            validSquare = false;
        }

    }

    public void lunchGame() {
        gameThread = new Thread(this);
        gameThread.start();
        main.info.setVisible(true);
    }

    public void setPieces(){
        pieces.add(new Pawn(WHITE, 0, 7));
        pieces.add(new Pawn(WHITE, 1, 7));
        pieces.add(new Pawn(WHITE, 2, 7));
        pieces.add(new Pawn(WHITE, 3, 7));
        pieces.add(new Pawn(WHITE, 4, 7));
        pieces.add(new Pawn(WHITE, 5, 7));
        pieces.add(new Pawn(WHITE, 6, 7));
        pieces.add(new Pawn(WHITE, 7, 7));

        pieces.add(new Tower(WHITE, 0, 8));
        pieces.add(new Tower(WHITE, 7, 8));

        pieces.add(new Horse(WHITE, 1, 8));
        pieces.add(new Horse(WHITE, 6, 8));

        pieces.add(new Bis(WHITE, 2, 8));
        pieces.add(new Bis(WHITE, 5, 8));

        pieces.add(new Queen(WHITE, 3, 8));

        pieces.add(new King(WHITE, 4, 8));

        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));

        pieces.add(new Tower(BLACK, 0, 0));
        pieces.add(new Tower(BLACK, 7, 0));

        pieces.add(new Horse(BLACK, 1, 0));
        pieces.add(new Horse(BLACK, 6, 0));

        pieces.add(new Bis(BLACK, 2, 0));
        pieces.add(new Bis(BLACK, 5, 0));

        pieces.add(new Queen(BLACK, 3, 0));

        pieces.add(new King(BLACK, 4, 0));
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear();
        target.addAll(source);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        try {
            Board.draw(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Piece p : pieces){
            try{
                p.draw(g2);
            } catch(Exception ignored){}
        }
    }


    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        setPieces();
        copyPieces(pieces, simPieces);
    }

    @Override
    public void run() {
        double drawInterval = 100000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if(gameover != -1){
               new End();
               break;
            }
        }

    }
}

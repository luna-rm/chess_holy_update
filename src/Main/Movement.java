package Main;

public class Movement {
    public String piece = "";
    public int number = 0;

    public int isFast = 0;
    public int reqSlay = 0;
    public int reqSin = 0;
    public int reqDiv = 0;

    public String desc = "";

    public Movement() {
        String piece = "";
        int number = 0;

        int isFast = 0;
        int reqSlay = 0;
        int reqSin = 0;
        int reqDiv = 0;

        String desc = "";
    }

    public Movement(String piece, int number, int isFast, int reqSlay, int reqSin, int reqDiv, String desc) {
        this.piece = piece;
        this.number = number;
        this.isFast = isFast;
        this.reqSlay = reqSlay;
        this.reqSin = reqSin;
        this.reqDiv = reqDiv;
        this.desc = desc;
    }
}

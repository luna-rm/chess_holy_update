package Main;

public class Movement {
    public String piece = "";
    public int number = -1;

    public int type = -1;
    public int isFast = -1;

    public int reqSlay = -1;
    public int reqSin = -1;
    public int reqDiv = -1;

    public String desc = "";

    public boolean noMov = false;

    public Movement() {
        this.piece = "";
        this.number = -1;
        this.type = -1;
        this.isFast = -1;
        this.reqSlay = -1;
        this.reqSin = -1;
        this.reqDiv = -1;
        this.desc = "";
    }

    public Movement(boolean noMov) {
        this.noMov = true;
    }

    public Movement(String piece, int number, int type, int isFast, int reqSlay, int reqSin, int reqDiv, String desc) {
        this.piece = piece;
        this.number = number;

        this.type = type;
        this.isFast = isFast;

        this.reqSlay = reqSlay;
        this.reqSin = reqSin;
        this.reqDiv = reqDiv;

        this.desc = desc;
    }
}

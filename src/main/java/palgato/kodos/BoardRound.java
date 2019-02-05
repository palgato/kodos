package palgato.kodos;

public class BoardRound {

    private int round;

    public BoardRound(int round) {
        this.round = round;
    }

    public int getRoundName() {
        return round;
    }

    public void updateRound() {
        this.round++;
    }
}

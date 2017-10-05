package nl.sogyo.mancala;

public class Player {
    private Kalaha kalaha;
    private RegularBox firstBox;
    private Player opponent;
    private boolean isActivePlayer;

    public Player(RegularBox firstBox){
        this.firstBox = firstBox;
        this.opponent = new Player(this);
        isActivePlayer = true;
    }
    public Player(Player opponent){
        this.opponent = opponent;
        isActivePlayer = false;
    }

    // methods
    public Player changePlayer(){
        isActivePlayer = false;
        opponent.isActivePlayer = true;
        return opponent;
    }

    public boolean getIsActivePlayer() {
        return isActivePlayer;
    }

    public RegularBox getFirstBox() {
        return firstBox;
    }

    public void setFirstBox(RegularBox firstBox) {
        this.firstBox = firstBox;
    }
}

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
        if(isActivePlayer) {
            isActivePlayer = false;
            opponent.isActivePlayer = true;
            return opponent;
        }
        else{
            isActivePlayer = true;
            opponent.isActivePlayer = false;
            return this;
        }
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

    public Kalaha getKalaha() {
        return kalaha;
    }

    public void setKalaha(Kalaha kalaha) {
        this.kalaha = kalaha;
    }

    public boolean canPlay() {
        boolean canPlay = firstBox.allNotEmpty();
        canPlay = opponent.canPlay(canPlay);
        return canPlay;
    }
    public boolean canPlay(boolean canPlay){
        if(canPlay){
            canPlay = firstBox.allNotEmpty();
        }
        return canPlay;
    }

    public int[] getScore(){
        int[] score = new int[2];
        if(!canPlay(false)){
            score[0] = kalaha.getStoneAmount();
            score[1] = 48-score[0];
        }
        else if(!opponent.canPlay(false)){
            score[1] = kalaha.getStoneAmount();
            score[0] = 48-score[1];
        }
        return score;
    }
}

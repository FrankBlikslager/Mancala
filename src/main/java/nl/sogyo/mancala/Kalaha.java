package nl.sogyo.mancala;

public class Kalaha extends Box {
    public Kalaha(int amountOfStones, int amountOfBoxes, int boxesLeftToInitialize, RegularBox startingBoxReference, Player player){
        this.player = player;
        this.player.setKalaha(this);
        Player opponent = player.getOpponent();
        stones = 0;
        boxesLeftToInitialize--;
        if(boxesLeftToInitialize>0) {
            nextBox = new RegularBox(amountOfStones, amountOfBoxes, boxesLeftToInitialize, startingBoxReference, opponent);
        }
        else {
            nextBox = startingBoxReference;
        }
    }
}

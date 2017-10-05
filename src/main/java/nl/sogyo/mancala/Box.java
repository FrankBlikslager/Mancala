package nl.sogyo.mancala;

public abstract class Box {
    Box nextBox;
    int stones;
    Player player;

    // methods

    public void moveStones(int stonesToDistribute){
        if(this instanceof Kalaha && !this.player.getIsActivePlayer()) { // if player!=opponent
            nextBox.moveStones(stonesToDistribute);
        }
        else{
            stones++;
            stonesToDistribute--;
            if (stonesToDistribute > 0) {
                nextBox.moveStones(stonesToDistribute);
            }
        }
    }

    public int getStoneAmount() {
        return stones;
    }

    public int getStoneAmountNextBox() {
        return nextBox.getStoneAmount();
    }
    public int getStoneAmountNextBox(int numberOfBoxesAway) {
        if(numberOfBoxesAway == 1) {
            return nextBox.getStoneAmount();
        }
        else {
            return nextBox.getStoneAmountNextBox(--numberOfBoxesAway);
        }
    }
}
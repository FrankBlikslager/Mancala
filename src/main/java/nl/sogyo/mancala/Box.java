package nl.sogyo.mancala;

public abstract class Box {
    Box nextBox;
    int stones;
    Player player;

    // methods
    protected void distributeStones(int stonesToDistribute){
        if(this instanceof Kalaha && !this.player.getIsActivePlayer()) { // if player!=opponent
            nextBox.distributeStones(stonesToDistribute);
        }
        else{
            addStones(1);
            stonesToDistribute--;
            actionAfterDistribution(stonesToDistribute);
        }
    }

    private void actionAfterDistribution(int stonesToDistribute) {
        if (stonesToDistribute > 0) {
            nextBox.distributeStones(stonesToDistribute);
        }
        else if(this instanceof Kalaha){
            // player has another turn
        }
        else if(this.player.getIsActivePlayer() && this.getStoneAmount()==1){
            ((RegularBox) this).captureStones();
            this.player.changePlayer();
        }
        else{
            this.player.changePlayer();
        }
    }

    public int getStoneAmount() {
        return stones;
    }
    public void addStones(int stonesToAdd){
        this.stones += stonesToAdd;
    }
    public void removeStones(){
        stones = 0;
    }

    public int getStoneAmountNextBox() {
        return nextBox.getStoneAmount();
    }
    public int getStoneAmountNextBox(int numberOfBoxesAway) {
        if (numberOfBoxesAway == 0)
            return getStoneAmount();
        else if(numberOfBoxesAway == 1) {
            return nextBox.getStoneAmount();
        }
        else {
            return nextBox.getStoneAmountNextBox(--numberOfBoxesAway);
        }
    }
}

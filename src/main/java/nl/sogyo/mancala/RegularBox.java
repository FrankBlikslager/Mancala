package nl.sogyo.mancala;

public class RegularBox {
    RegularBox nextBox;
    private int stones;

    // constructors
    public RegularBox(){}

    public RegularBox(int amountOfNextNewBoxes){
        if(amountOfNextNewBoxes>0) {
            amountOfNextNewBoxes--;
            nextBox = new RegularBox(amountOfNextNewBoxes);
        }
    }

    public RegularBox(int amountOfStones, int amountOfNextNewBoxes){
        stones = amountOfStones;
        if(amountOfNextNewBoxes>0) {
            amountOfNextNewBoxes--;
            nextBox = new RegularBox(amountOfStones, amountOfNextNewBoxes);
        }
    }

    // methods
    public void moveStones() {
        if(nextBox!=null){
            nextBox.moveStones(stones);
        }
        stones = 0;
    }
    public void moveStones(int stonesToDistribute){
        stones++;
        stonesToDistribute--;
        if(stonesToDistribute>0){
            nextBox.moveStones(stonesToDistribute);
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

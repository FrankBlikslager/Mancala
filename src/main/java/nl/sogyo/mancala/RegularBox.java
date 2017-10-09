package nl.sogyo.mancala;

public class RegularBox extends Box{

    // constructors
    public RegularBox(){}
    public RegularBox(int amountOfStones, int amountOfBoxes){
        Player player = new Player(this);
        this.player = player;
        stones = amountOfStones;
        int boxesLeftToInitialize = amountOfBoxes-1;
        if(boxesLeftToInitialize>0) {
            RegularBox startingBoxReference = this;
            nextBox = new RegularBox(amountOfStones, amountOfBoxes, boxesLeftToInitialize, startingBoxReference, player);
        }
    }
    public RegularBox(int amountOfStones, int amountOfBoxes, int boxesLeftToInitialize, RegularBox startingBoxReference, Player player){
        stones = amountOfStones;
        this.player = player;
        if(player.getFirstBox()==null){
            player.setFirstBox(this);
        }
        boxesLeftToInitialize--;
        if (amountOfBoxes==14 && (boxesLeftToInitialize == 1 || boxesLeftToInitialize == 8)){
            nextBox = new Kalaha(amountOfStones, amountOfBoxes, boxesLeftToInitialize, startingBoxReference, player);
        }
        else if (boxesLeftToInitialize>0){
            nextBox = new RegularBox(amountOfStones, amountOfBoxes, boxesLeftToInitialize, startingBoxReference, player);
        }
        else if (boxesLeftToInitialize == 0){
            nextBox = startingBoxReference;
        }
    }

    // methods
    public void moveStones() {
        int moveStones = stones;
        stones = 0;
        if(nextBox!=null){
            nextBox.moveStones(moveStones);
        }
    }

    public void playBox(int boxNumber) {
        int boxesToMove = boxNumber-1;
        if (boxesToMove==0 && this.player.getIsActivePlayer()){
            if(this.getStoneAmount() != 0) {
                this.moveStones();
                this.player.changePlayer();
            }
        }
        else if(boxesToMove==0 && !this.player.getIsActivePlayer()){
            // do nothing
        }
        else {
            if(nextBox instanceof RegularBox) {
                ((RegularBox) nextBox).playBox(boxesToMove);
            }
            else { // box is Kalaha
                if(boxesToMove!=1) {
                    boxesToMove--;
                    ((RegularBox) nextBox.nextBox).playBox(boxesToMove);
                }
            }
        }
    }

    public void captureStones() {
        int numberOfBoxesAway = getOpposingBox();
        if(getStoneAmountNextBox(numberOfBoxesAway)!=0) {
            int stonesToKalaha = stones;
            stones = 0;
            stonesToKalaha = stonesToKalaha + countToKalaha();
            player.getKalaha().addStones(stonesToKalaha);
        }
    }

    public int countToKalaha(){
        int counter = 0;
        int stonesToKalaha = countToKalaha(counter);
        return stonesToKalaha;
    }
    public int countToKalaha(int counter){
        counter++;
        int stonesToKalaha;
        if(nextBox instanceof RegularBox) {
            stonesToKalaha= ((RegularBox) nextBox).countToKalaha(counter);
        }
        else{
            stonesToKalaha = ((RegularBox) nextBox.nextBox).countFromKalaha(counter);
        }
        return stonesToKalaha;
    }

    public int countFromKalaha(int counter){
        counter--;
        int stonesToKalaha;
        if(counter == 0){
            stonesToKalaha = getStoneAmount();
            stones = 0;
        }
        else{
            stonesToKalaha = ((RegularBox) nextBox).countFromKalaha(counter);
        }
        return stonesToKalaha;
    }

    public int getOpposingBox(){
        int counter = 0;
        counter = getOpposingBox(counter);
        return counter;
    }
    public int getOpposingBox(int counter){
        counter++;
        if(nextBox instanceof Kalaha){
            counter = counter*2;
        }
        else{
            counter = ((RegularBox) nextBox).getOpposingBox(counter);
        }
        return counter;
    }

    public boolean allNotEmpty() {
        if (getStoneAmount()!=0){
            return true;
        }
        else{
            if(nextBox instanceof Kalaha){
                return false;
            }
            else{
                return ((RegularBox) nextBox).allNotEmpty();
            }
        }
    }
}

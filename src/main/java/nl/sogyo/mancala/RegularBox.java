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
    protected RegularBox(int amountOfStones, int amountOfBoxes, int boxesLeftToInitialize, RegularBox startingBoxReference, Player player){
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
    protected void moveStones() {
        int stonesToDistrubute = stones;
        stones = 0;
        if(nextBox!=null){
            nextBox.distributeStones(stonesToDistrubute);
        }
    }

    public void playBox(int boxNumber) {
        int boxesToMove = boxNumber-1;
        if (boxesToMove == 0){
            playCurrentBox();
        }
        else {
            playOtherBox(boxesToMove);
        }
    }

    private void playCurrentBox() {
        if (this.player.getIsActivePlayer() && this.getStoneAmount() != 0){
            this.moveStones();
        }
    }

    private void playOtherBox(int boxesToMove) {
        if(nextBox instanceof RegularBox) {
            ((RegularBox) nextBox).playBox(boxesToMove);
        }
        else if(boxesToMove!=1){
            boxesToMove--;
            ((RegularBox) nextBox.nextBox).playBox(boxesToMove);
        }
    }

    protected void captureStones() {
        int numberOfBoxesAway = getDistanceToOpposingBox();
        if(getStoneAmountNextBox(numberOfBoxesAway)!=0) {
            int stonesToKalaha = getStoneAmount();
            removeStones();
            stonesToKalaha += getCapturedStones();
            player.getKalaha().addStones(stonesToKalaha);
        }
    }

    private int getDistanceToOpposingBox(){
        int counter = 0;
        counter = getOpposingBox(counter);
        return counter;
    }
    private int getOpposingBox(int counter){
        counter++;
        if(nextBox instanceof Kalaha){
            counter = counter*2;
        }
        else{
            counter = ((RegularBox) nextBox).getOpposingBox(counter);
        }
        return counter;
    }

    private int getCapturedStones(){
        int counter = 0;
        int stonesToKalaha = countToKalaha(counter);
        return stonesToKalaha;
    }

    private int countToKalaha(int counter){
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

    private int countFromKalaha(int counter){
        counter--;
        int stonesToKalaha;
        if(counter == 0){
            stonesToKalaha = getStoneAmount();
            removeStones();
        }
        else{
            stonesToKalaha = ((RegularBox) nextBox).countFromKalaha(counter);
        }
        return stonesToKalaha;
    }

    protected boolean allNotEmpty() {
        if (getStoneAmount()!=0){
            return true;
        }
        else if(nextBox instanceof Kalaha){
            return false;
        }
        else{
                return ((RegularBox) nextBox).allNotEmpty();
        }
    }
}

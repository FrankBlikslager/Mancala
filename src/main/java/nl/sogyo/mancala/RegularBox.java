package nl.sogyo.mancala;

public class RegularBox extends Box{

    // constructors
    public RegularBox(){}
    public RegularBox(int amountOfBoxes){
        amountOfBoxes--;
        if(amountOfBoxes>0) {
            nextBox = new RegularBox(amountOfBoxes);
        }
    }
    public RegularBox(int amountOfStones, int amountOfBoxes){
        Player player = new Player(this);
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
        if (boxesToMove==0){
            this.moveStones();
        }
        else {
            if((""+nextBox.getClass()).equals("class nl.sogyo.mancala.RegularBox")) {
                ((RegularBox) nextBox).playBox(boxesToMove);
            }
        }
    }
}

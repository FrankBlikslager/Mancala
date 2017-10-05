package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    // moveStones()
    @Test
    public void stonesLeftAfterMove() {
        RegularBox box = new RegularBox();
        box.moveStones();
        Assert.assertEquals(0,box.getStoneAmount());
    }

    @Test
    public void testMovedStoneToNextBox() {
        RegularBox box = new RegularBox(2);
        int startingStonesNextBox = box.getStoneAmountNextBox();

        box.moveStones();

        Assert.assertEquals(startingStonesNextBox+1, box.getStoneAmountNextBox());
    }

    @Test
    public void testMovedStonesToNextBoxes(){
        RegularBox box = new RegularBox(2,3);

        box.moveStones();

        Assert.assertEquals(3, box.getStoneAmountNextBox(1));
    }

    @Test
    public void checkMovedStonesNotTooFar(){
        RegularBox box = new RegularBox(2,4);

        box.moveStones();

        Assert.assertEquals(2, box.getStoneAmountNextBox(3));
    }

    @Test
    public void checkIfLoopIsClosed(){
        RegularBox box = new RegularBox(5,4);

        box.moveStones();

        Assert.assertEquals(5+2, box.getStoneAmountNextBox());
    }

    @Test
    public void checkBeginningBoxIfLoopIsClosed(){
        RegularBox box = new RegularBox(4,4);

        box.moveStones();

        Assert.assertEquals(1, box.getStoneAmount());
    }

    @Test
    public void playBoxThatIsNotFirst(){
        RegularBox box = new RegularBox(2,5);

        box.playBox(3);

        Assert.assertEquals(0, box.getStoneAmountNextBox(2));
    }

    @Test
    public void testIfFirstKalahaIsEmpty(){
        RegularBox box = new RegularBox(4,14);
        Assert.assertEquals(0, box.getStoneAmountNextBox(6));
    }

    @Test
    public void testIfLastKalahaIsEmpty(){
        RegularBox box = new RegularBox(4,14);
        Assert.assertEquals(0, box.getStoneAmountNextBox(13));
    }

    @Test
    public void testIfKalahaCannotBePlayed(){
        RegularBox box = new RegularBox(4,14);

        box.playBox(6);
        box.playBox(7);

        Assert.assertEquals(1, box.getStoneAmountNextBox(6));
    }

    @Test
    public void testIfGameIsStillCircularWithKalahas(){
        RegularBox box = new RegularBox(9,14);
        box.playBox(6);
        Assert.assertEquals(10, box.getStoneAmount());
    }

    @Test
    public void isKalahaOpponentSkipped(){
        RegularBox box = new RegularBox(8,14);
        box.playBox(6);
        Assert.assertEquals(0, box.getStoneAmountNextBox(13));
    }
    /*
     * Next tests:
     * -
     * - make board of 12 (10, 8, 6, 4)
     */
}
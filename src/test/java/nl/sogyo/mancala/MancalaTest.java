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
        RegularBox box = new RegularBox(1);
        int startingStonesNextBox = box.getStoneAmountNextBox();

        box.moveStones();

        Assert.assertEquals(startingStonesNextBox+1, box.getStoneAmountNextBox());
    }

    @Test
    public void testMovedStonesToNextBoxes(){
        RegularBox box = new RegularBox(2,2);

        box.moveStones();

        Assert.assertEquals(3, box.getStoneAmountNextBox(1));
    }

    @Test
    public void checkMovedStonesNotTooFar(){
        RegularBox box = new RegularBox(2,3);

        box.moveStones();

        Assert.assertEquals(2, box.getStoneAmountNextBox(3));
    }

    /*
     * Next tests:
     * - close circle: check on self if moveStones() can loop around
     *
     */
}
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

    @Test
    public void canPlayersOnlyPlayOwnBoxes(){
        RegularBox box = new RegularBox(4,14);
        box.playBox(8);
        Assert.assertEquals(4, box.getStoneAmountNextBox(7));
    }

    @Test
    public void switchTurnsIfPlayerHasPlayed(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(4);
        box.playBox(8);
        Assert.assertEquals(0, box.getStoneAmountNextBox(7));
    }

    @Test
    public void dontSwitchPlayerIfMoveWasInvalid(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(8);
        box.playBox(7);
        box.playBox(6);
        Assert.assertEquals(5, box.getStoneAmountNextBox(7));
    }

    @Test
    public void dontSwitchPlayerIfMoveEndedAtKalaha(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(3);
        box.playBox(4);
        Assert.assertEquals(2, box.getStoneAmountNextBox(6));
    }

    @Test
    public void captureStonesOtherPlayer(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(6);
        box.playBox(13);
        box.playBox(1);
        Assert.assertEquals(7,box.getStoneAmountNextBox(6));
    }



    /*
     * Next tests:
     * - hit other player
     * - hit other player, but not if his box is empty
     * - check-end game
     * - (make board of 12 (10, 8, 6, 4))
     */
}
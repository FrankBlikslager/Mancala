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
        RegularBox box = new RegularBox(1,2);
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
        RegularBox box = new RegularBox(5,4);

        box.moveStones();

        Assert.assertEquals(7, box.getStoneAmountNextBox());
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

    @Test
    public void captureStonesOtherPlayerCheckIfOwnBoxIsEmpty(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(6);
        box.playBox(13);
        box.playBox(1);
        Assert.assertEquals(0,box.getStoneAmount());
    }

    @Test
    public void captureStonesOtherPlayerCheckIfOtherBoxIsEmpty(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(6);
        box.playBox(13);
        box.playBox(1);
        Assert.assertEquals(0,box.getStoneAmountNextBox(7));
    }

    @Test
    public void captureStonesOtherPlayerCheckIfOtherBoxIsEmpty2(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(5);
        box.playBox(8);
        box.playBox(1);
        Assert.assertEquals(0,box.getStoneAmountNextBox(8));
    }

    @Test
    public void doNotCaptureStonesOtherPlayer(){
        RegularBox box = new RegularBox(4, 14);
        box.playBox(6);
        box.playBox(8);
        box.playBox(2);
        Assert.assertEquals(1,box.getStoneAmountNextBox(5));
    }

    @Test
    public void checkIfGameIsEnded(){
        RegularBox box = new RegularBox(4, 14);
        Assert.assertEquals(true,box.player.canPlay());
    }

    @Test
    public void checkIfGameIsEnded2(){
        RegularBox box = new RegularBox(0, 14);
        Assert.assertEquals(false,box.player.canPlay());
    }

    @Test
    public void checkIfGameIsEnded3(){
        RegularBox box = new RegularBox(0, 14);
        box.addStones(1);
        Assert.assertEquals(false,box.player.canPlay());
    }

    @Test
    public void checkIfGameIsEnded4(){
        RegularBox box = new RegularBox(0, 14);
        box.nextBox.nextBox.nextBox.nextBox.nextBox.addStones(2);
        box.playBox(6);
        Assert.assertEquals(false,box.player.canPlay());
    }

    @Test
    public void checkIfScoreIsGiven(){
        RegularBox box = new RegularBox(0, 14);
        box.nextBox.nextBox.nextBox.nextBox.nextBox.addStones(1);
        box.playBox(6);
        int score[] = box.player.getScore();
        Assert.assertEquals(47, score[1]);
    }

    @Test
    public void checkIfEmptyBoxCannotBePlayed(){
        RegularBox box = new RegularBox(0,14);
        box.playBox(1);
        Assert.assertEquals(0,box.getStoneAmountNextBox());
    }

}
package inf112.skeleton.app.cards;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AIHandTest {
    private AIHand hand;
     @Before
     public void setup(){
         ProgramDeck deck = new ProgramDeck("testAIHand.txt");
         this.hand = new AIHand(deck.deal(9));
     }

    @Test
    public void containsMove() {
        assertTrue(hand.containsMove());
    }

    @Test
    public void findMove() {
        ProgramCard program = hand.findMove();
        assertTrue(program.getMove().isMove());
    }

    @Test
    public void containsTurn() {
         assertTrue(hand.containsTurn());
    }

    @Test
    public void findTurn() {
         ProgramCard programCard = hand.findTurn();
         assertFalse(programCard.getMove().isMove());
    }

    @Test
    public void contains() {
         assertTrue(hand.contains(Program.BACK));
    }

    @Test
    public void containsNumberOfPrograms() {
        assertTrue(hand.contains(Program.MOVE2,2));
    }

    @Test
    public void get() {
         ProgramCard programCard = hand.get(Program.MOVE3);
         assertEquals(Program.MOVE3, programCard.getMove());
    }
}
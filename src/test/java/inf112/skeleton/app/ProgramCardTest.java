package inf112.skeleton.app;

import inf112.skeleton.app.cards.Movement;
import inf112.skeleton.app.cards.ProgramCard;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramCardTest {

    @Test
    public void TestUTurn() {
        int priority=60;
        String move ="U Turn";
        ProgramCard card = new ProgramCard(priority,move);
        assertTrue(priority==card.getPriority());
        assertTrue(card.getMove()== Movement.U);

    }

    @Test
    public void TestMove3() {
        int priority = 99;
        String move = "Move 3";
        ProgramCard card = new ProgramCard(priority,move);
        assertTrue(priority==card.getPriority());
        assertTrue(card.getMove()==Movement.MOVE3);
    }
}
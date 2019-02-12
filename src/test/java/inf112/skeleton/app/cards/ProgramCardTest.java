package inf112.skeleton.app.cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramCardTest {

    @Test
    public void TestUTurn() {
        int priority=60;
        String move ="U Turn";
        ProgramCard card = new ProgramCard(priority,move);
        assertEquals(priority,card.getPriority());
        assertSame(card.getMove(), Program.U);

    }

    @Test
    public void TestMove3() {
        int priority = 99;
        String move = "Move 3";
        ProgramCard card = new ProgramCard(priority,move);
        assertEquals(priority,card.getPriority());
        assertSame(card.getMove(), Program.MOVE3);
    }
}
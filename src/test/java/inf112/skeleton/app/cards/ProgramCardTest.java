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
        assertSame(card.getProgram(), Program.U);

    }
    @Test
    public void TestToString() {
        int priority=60;
        String move ="U Turn";
        ProgramCard card = new ProgramCard(priority,move);

        assertEquals(move, card.toString());

    }

    @Test
    public void TestMove3() {
        int priority = 99;
        String move = "Move 3";
        ProgramCard card = new ProgramCard(priority,move);
        assertEquals(priority,card.getPriority());
        assertSame(card.getProgram(), Program.MOVE3);
    }

    @Test
    public void compareTo() {
        ProgramCard card1 = new ProgramCard(90,Program.MOVE3);
        ProgramCard card2 = new ProgramCard(60,Program.U);
        ProgramCard card3 = new ProgramCard(88,Program.RIGHT);
        ProgramCard card4 = new ProgramCard(60,Program.BACK);

        assertEquals(card1.compareTo(card2),1);
        assertEquals(card4.compareTo(card3),-1);
        assertEquals(card2.compareTo(card4),0);
    }
    @Test
    public void totalMoves2() {
        ProgramCard p1 = new ProgramCard(20, Program.MOVE1);
        ProgramCard p2 = new ProgramCard(500,Program.MOVE2);
        ProgramCard p3 = new ProgramCard(890, Program.MOVE3);
        ProgramCard p4 = new ProgramCard(400, Program.BACK);

        assertEquals(1, p1.getProgram().totalMoves());
        assertEquals(2, p2.getProgram().totalMoves());
        assertEquals(3, p3.getProgram().totalMoves());
        assertEquals(1, p4.getProgram().totalMoves());
    }
}
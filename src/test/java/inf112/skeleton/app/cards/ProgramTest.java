package inf112.skeleton.app.cards;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProgramTest {

    @Test
    public void totalMoves() {
        Program p1 = Program.MOVE1;
        Program p2 = Program.MOVE2;
        Program p3 = Program.MOVE3;
        Program p4 = Program.BACK;

        assertEquals(1, p1.totalMoves());
        assertEquals(2, p2.totalMoves());
        assertEquals(3, p3.totalMoves());
        assertEquals(1, p4.totalMoves());
    }
}
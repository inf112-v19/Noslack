package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrientationTest {


    @Test
    public void rotateU() {
        Orientation o1 = Orientation.FACING_NORTH;
        o1 = o1.rotate(Program.U);
        assertEquals(Orientation.FACING_SOUTH,o1);
    }
    @Test
    public void rotateRight() {
        Orientation o2 = Orientation.FACING_SOUTH;
        o2 = o2.rotate(Program.RIGHT);
        assertEquals(Orientation.FACING_WEST,o2);
    }
    @Test
    public void rotateLeft() {
        Orientation o2 = Orientation.FACING_SOUTH;
        o2 = o2.rotate(Program.LEFT);
        assertEquals(Orientation.FACING_EAST,o2);
    }
    @Test
    public void rotateMove3() {
        Orientation o3 = Orientation.FACING_EAST;
        o3 = o3.rotate(Program.MOVE3);
        assertEquals(Orientation.FACING_EAST,o3);
    }
}
package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AICoordinateTest {
    private AICoordinate coordinate;

    @Before
    public void setup(){
        Coordinate coordinate = new Coordinate(4,4,Orientation.FACING_NORTH);
        this.coordinate = new AICoordinate(coordinate);
    }

    @Test
    public void moveAICoordinate1() {
        this.coordinate.moveAICoordinate(Program.BACK);
        assertEquals(3, this.coordinate.getRow());
        assertEquals(4, this.coordinate.getColumn());
    }

    @Test
    public void moveAICoordinate2() {
        this.coordinate.moveAICoordinate(Program.RIGHT);
        this.coordinate.moveAICoordinate(Program.MOVE2);
        assertEquals(4, this.coordinate.getRow());
        assertEquals(6, this.coordinate.getColumn());
    }

    @Test
    public void moveAICoordinate3() {
        this.coordinate.moveAICoordinate(Program.LEFT);
        this.coordinate.moveAICoordinate(Program.MOVE3);
        assertEquals(4, this.coordinate.getRow());
        assertEquals(1, this.coordinate.getColumn());
    }

    @Test
    public void moveAICoordinate4() {
        this.coordinate.moveAICoordinate(Program.U);
        this.coordinate.moveAICoordinate(Program.BACK2);
        assertEquals(6, this.coordinate.getRow());
        assertEquals(4, this.coordinate.getColumn());
    }

    @Test
    public void moveAICoordinate5() {
        this.coordinate.moveAICoordinate(Program.RIGHT);
        this.coordinate.moveAICoordinate(Program.MOVE4);
        assertEquals(4, this.coordinate.getRow());
        assertEquals(8, this.coordinate.getColumn());
    }
}
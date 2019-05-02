package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConveyorTest {

    @Test
    public void conveyorInitialized() {
        Conveyor conveyor = new Conveyor(Orientation.FACING_NORTH, false, 0,false,false);
        assertNotEquals(conveyor, null);
    }

    @Test
    public void conveyorMovesPlayer() {
        TileGrid tileGrid = new TileGrid("conveyorTestMap.txt");
        tileGrid.getRobot(0).setCurrentMove(Program.MOVE1);
        Coordinate oldPos = tileGrid.getRobotPosition(0);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }

        assertEquals(oldPos.moveCoordinate(1,-2),tileGrid.getRobotPosition(0));
    }

    @Test
    public void fastConveyorMovesPlayer() {
        TileGrid tileGrid = new TileGrid("fastConveyorTestMap.txt");
        tileGrid.getRobot(0).setCurrentMove(Program.MOVE1);
        Coordinate oldPos = tileGrid.getRobotPosition(0);

        tileGrid.continueMove(0);
        tileGrid.activateTiles();

        assertEquals(oldPos.moveCoordinate(1,-2),tileGrid.getRobotPosition(0));
    }

    @Test
    public void rotatesLeft() {
        TileGrid tileGrid = new TileGrid("rotateConveyorTestMap.txt");
        Orientation oldO = tileGrid.getRobot(0).getOrientation();
        tileGrid.getRobot(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(oldO.rotate(Program.LEFT),tileGrid.getRobot(0).getOrientation());
    }

}
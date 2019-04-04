package inf112.skeleton.app.gameobjects.tiletypes;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConveyorTest {

    @Test
    public void pusherInitialized() {
        Pusher pusher = new Pusher(Orientation.FACING_NORTH);
        assertNotEquals(pusher, null);
    }

    @Test
    public void conveyorInitialized() {
        Conveyor conveyor = new Conveyor(Orientation.FACING_NORTH, false, 0);
        assertNotEquals(conveyor, null);
    }

    @Test
    public void conveyorMovesPlayer() {
        TileGrid tileGrid = new TileGrid("conveyorTestMap.txt");
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);

        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }

        assertEquals(new Coordinate(1,0),tileGrid.getPlayerPosition(0));
    }

    @Test
    public void fastConveyorMovesPlayer() {
        TileGrid tileGrid = new TileGrid("fastConveyorTestMap.txt");
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(new Coordinate(1,0),tileGrid.getPlayerPosition(0));
    }

    @Test
    public void rotatesLeft() {
        TileGrid tileGrid = new TileGrid("rotateConveyorTestMap.txt");
        Orientation oldO = tileGrid.getPlayer(0).getOrientation();
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(oldO.rotate(Program.LEFT),tileGrid.getPlayer(0).getOrientation());
    }

    @Test
    public void playerCanPass() {
        TileGrid tileGrid = new TileGrid("conveyorTestMap.txt");
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE2);
        for(int i =0; i<2; i++){
            tileGrid.continueMove(0);
            tileGrid.activateTiles();
        }
        assertEquals(new Coordinate(2,1),tileGrid.getPlayerPosition(0));
    }

}
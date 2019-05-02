package inf112.skeleton.app;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.cards.ProgramDeck;
import inf112.skeleton.app.cards.RRCard;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class TileGridTest {

    @Test
    public void movePlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        grid.getRobot(0).setCurrentMove(Program.MOVE1);
        Coordinate coordinate = grid.getRobotPosition(0);
        grid.continueMove(0);
        Coordinate coordinate2 = grid.getRobotPosition(0);
        Coordinate newPosition = new Coordinate(1,3);

        assertNotEquals(coordinate,coordinate2);
        assertEquals(newPosition,coordinate2);
    }

    @Test
    public void getPlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        assertEquals(GameObjectType.ROBOT,grid.getRobot(0).getGameObjectType());
    }

    @Test
    public void getCoordinatesOfPlayer() {
        TileGrid grid = new TileGrid("onePlayerTestMap.txt");
        Coordinate coordinate = new Coordinate(1,2);
        assertEquals(coordinate,grid.getRobotPosition(0));
    }

    @Test
    public void getCoordinatesOfPlayers() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        Coordinate coordinate1 = new Coordinate(1,2);
        Coordinate coordinate2 = new Coordinate(4,1);
        assertEquals(coordinate1,grid.getRobotPosition(1));
        assertEquals(coordinate2,grid.getRobotPosition(0));
    }

    @Test
    public void getRows() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(6,grid.getRows());
    }

    @Test
    public void getColumns() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(6,grid.getColumns());
    }

    @Test
    public void getPlayersInitiated() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(2,grid.getRobotsInitiated());
    }

    @Test
    public void firePlayerLaser() {
        TileGrid grid = new TileGrid("fireLaserTestMap.txt");
        grid.fireRobotLaser(0);

        assertFalse(grid.getTile(0,0).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,3).hasGameObject(GameObjectType.LASER_BEAM));
    }

    @Test
    public void firePlayerLaser2() {
        TileGrid grid = new TileGrid("fireLaserTestMap2.txt");
        grid.fireRobotLaser(1);
        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,3).hasGameObject(GameObjectType.LASER_BEAM));
    }


    @Test
    public void removePlayerLaser() {
        TileGrid grid = new TileGrid("fireLaserTestMap.txt");
        grid.fireRobotLaser(0);
        assertTrue(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertTrue(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));

        grid.removeRobotLaser(0);

        assertFalse(grid.getTile(0,1).hasGameObject(GameObjectType.LASER_BEAM));
        assertFalse(grid.getTile(0,2).hasGameObject(GameObjectType.LASER_BEAM));
    }

    @Test
    public void robotQueue() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        ArrayList<RRCard> deck = new ProgramDeck("testQueue1.txt").deal(5);
        ArrayList<ProgramCard> deck1 = new ArrayList<>();
        for(RRCard card : deck){
            deck1.add((ProgramCard) card);
        }
        deck = new ProgramDeck("testQueue2.txt").deal(5);
        ArrayList<ProgramCard> deck2 = new ArrayList<>();
        for(RRCard card : deck){
            deck2.add((ProgramCard) card);
        }
        grid.getRobot(0).pushProgram(deck1);
        grid.getRobot(1).pushProgram(deck2);

        Stack<Integer> list = grid.robotQueue();
        assertEquals(2, list.size());
        assertTrue(0==list.pop());
        assertTrue(1==list.pop());
    }

    @Test
    public void robotInLine() {
        TileGrid grid = new TileGrid("twoPlayersTestMap.txt");
        assertEquals(1, grid.robotInLine(0));
        assertEquals(0, grid.robotInLine(1));

    }
}
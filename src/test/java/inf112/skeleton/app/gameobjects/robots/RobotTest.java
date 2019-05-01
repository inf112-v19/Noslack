package inf112.skeleton.app.gameobjects.robots;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.gameobjects.Robots.HunterAI;
import inf112.skeleton.app.gameobjects.Robots.IRobot;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {
    private IRobot robot = new HunterAI(0);

    @Test
    public void robotInitialized(){
        assertNotEquals(robot,null);
    }
    @Test
    public void robotOnMap(){
        TileGrid tileGrid = new TileGrid("robotTestMap.txt");
        IRobot robot = tileGrid.getRobot(0);
        assertEquals("Robot 0",robot.toString());
    }

    @Test
    public void robotHas9HP(){
        assertEquals(9, robot.getHealth());
    }

}
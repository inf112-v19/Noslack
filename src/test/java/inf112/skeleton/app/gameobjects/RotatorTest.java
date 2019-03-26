package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotatorTest {

    Rotator clockwiseRotator = new Rotator(GameObjectType.ROTATOR_CLOCKWISE);
    Player player = new Player(0);
    TileGrid tileGrid = new TileGrid("rotatorTestMap.txt");

    @Test
    public void ClockwiseRotatorRotatesClockwise(){

    }
}
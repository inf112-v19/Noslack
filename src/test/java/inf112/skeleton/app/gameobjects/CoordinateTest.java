package inf112.skeleton.app.gameobjects;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void equals() {
        Coordinate cor1 = new Coordinate(1 ,1);
        Coordinate cor2 = new Coordinate(2,1);
        assertEquals(cor1, cor1);
        assertNotEquals(cor1, cor2);
    }

    @Test
    public void getRow() {
        int row = 2;
        int column = 3;
        Coordinate cor = new Coordinate(row, column);
        assertEquals(row,cor.getRow());
    }

    @Test
    public void getColumn() {
        int row = 2;
        int column = 3;
        Coordinate cor = new Coordinate(row, column);
        assertEquals(column,cor.getColumn());
    }

    @Test
    public void moveCoordinate() {
        Coordinate oldCoordinate = new Coordinate(3,2);
        Coordinate newCoordinate = oldCoordinate.moveCoordinate(2,-1);
        assertEquals(new Coordinate(5,1), newCoordinate);
    }
    @Test
    public void moveInDirection(){
        Orientation[] directions = {Orientation.FACING_NORTH,Orientation.FACING_EAST,Orientation.FACING_WEST,Orientation.FACING_SOUTH};
        Coordinate oldCoordinate = new Coordinate(3,3);
        Orientation orientation;
        int[] correctResultRow = {1,0,0,-1};
        int[] correctResultCol = {0,1,-1,0};
        for (int i = 0; i < 4; i++) {
            orientation = directions[i];
            oldCoordinate.setOrientation(orientation);

            assertEquals(oldCoordinate.moveCoordinate(),new Coordinate(3+correctResultRow[i],3+correctResultCol[i]));
        }
    }

    @Test
    public void orientationToPositionWest() {
        Coordinate coordinate1 = new Coordinate(3,3);
        Coordinate coordinate2 = new Coordinate(3, 1);
        assertEquals(Orientation.FACING_WEST, coordinate1.orientationToPosition(coordinate2));
    }
    @Test
    public void orientationToPositionEast() {
        Coordinate coordinate1 = new Coordinate(2,0);
        Coordinate coordinate2 = new Coordinate(3, 4);
        assertEquals(Orientation.FACING_EAST, coordinate1.orientationToPosition(coordinate2));
    }
    @Test
    public void orientationToPositionSouth() {
        Coordinate coordinate1 = new Coordinate(3,3);
        Coordinate coordinate2 = new Coordinate(0, 1);
        assertEquals(Orientation.FACING_SOUTH, coordinate1.orientationToPosition(coordinate2));
    }
}
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
}
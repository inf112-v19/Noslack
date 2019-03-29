package inf112.skeleton.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileGridBuilderTest {
    private TileGridBuilder builder = new TileGridBuilder("onePlayerTestMap.txt");

    @Test
    public void getRows() {
        assertEquals(4,builder.getRows());
    }

    @Test
    public void getColumns() {
        assertEquals(4,builder.getColumns());
    }

    @Test
    public void getFlagsInitiated() {
        assertEquals(2,builder.getFlagsInitiated());
    }

    @Test
    public void getPlayersInitiated() {
        assertEquals(1,builder.getPlayersInitiated());
    }
}
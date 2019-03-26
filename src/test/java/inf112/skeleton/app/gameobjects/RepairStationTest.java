package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.TileGrid;
import inf112.skeleton.app.cards.Program;
import org.junit.Test;

import static org.junit.Assert.*;

public class RepairStationTest {

    @Test
    public void playerRepairs() {
        TileGrid tileGrid = new TileGrid("repairTestMap.txt");
        int oldHP = tileGrid.getPlayer(0).getHealth();
        tileGrid.getPlayer(0).setCurrentMove(Program.MOVE1);
        tileGrid.continueMove(0);
        int newHP = tileGrid.getPlayer(0).getHealth();
        assertEquals(newHP,oldHP+1);
    }

}
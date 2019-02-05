package inf112.skeleton.app;

import java.util.Iterator;

public class TileGrid{
    private Tile[][] tileGrid;
    int rows;
    int columns;

    /**
     * Default constructor.
     */
    public TileGrid(){
        this.rows = 12;
        this.columns = 12;
        tileGrid = new Tile[rows][columns];
        initiateTiles(tileGrid);
    }

    /**
     * Constructor with specifications.
     * @param rows: The amount of rows in the grid.
     * @param columns: The amount of columns in the grid.
     */
    public TileGrid(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        tileGrid = new Tile[rows][columns];
        initiateTiles(tileGrid);
    }

    /**
     *
     * @param row
     * @param column
     * @return Tile at specified coordinate
     */
    public Tile getTile(int row, int column){
        return tileGrid[row][column];
    }

    /**
     * Initiates every tile in the grid
     * @param tileGrid
     *
     * Todo:
     * Implement reading tile-layout from file, or randomisation.
     */
    public void initiateTiles(Tile[][] tileGrid){
        for(int row = 0; row<rows; row++){
            for(int column = 0; column<columns; column++){
                // Temporarily set to STANDARD_TILE
                tileGrid[row][column] = new Tile(GameObjectType.STANDARD_TILE);
            }
        }
    }

}

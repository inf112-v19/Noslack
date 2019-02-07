package inf112.skeleton.app;

import inf112.skeleton.app.gameobjects.ConveyorNorth;
import inf112.skeleton.app.gameobjects.GameObjectType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private final String fileName = "./assets/maplayout.txt";

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
     * @param row: row of the requested tile
     * @param column: column of the requested tile
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
    private void initiateTiles(Tile[][] tileGrid){

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            for(int row = 0; row<rows; row++){
                String nextTileTypeLine = bufferedReader.readLine();
                for(int column = 0; column<columns; column++){
                    // Temporarily set to STANDARD_TILE
                    int nextTileTypeAsInt = nextTileTypeLine.charAt(column*2)-48; // *2 is to jump over spaces, -48 is to convert from ascii to int.
                    GameObjectType nextTileType = stringToGameObjectType(nextTileTypeAsInt);
                    tileGrid[row][column] = new Tile(GameObjectType.STANDARD_TILE);

                    // Adding objects on top of tile
                    if(nextTileTypeAsInt > 1){ // If tile type is not standardTile
                        switch(nextTileType){
                            case CONVEYOR_NORTH:  tileGrid[row][column].addObjectOnTile(new ConveyorNorth());
                        }

                    }
                }
            }

            bufferedReader.close();
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    private GameObjectType stringToGameObjectType(int nextTileTypeAsInt){
        switch(nextTileTypeAsInt){
            case 1: return GameObjectType.STANDARD_TILE;
            case 2: return GameObjectType.CONVEYOR_NORTH;
            default: return GameObjectType.STANDARD_TILE;
        }
    }

}

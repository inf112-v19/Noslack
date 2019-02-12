package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import inf112.skeleton.app.gameobjects.ConveyorNorth;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private final String fileName = "./assets/maplayout.txt";
    private Player[] players;
    private int playersInitiated; // How many players have been initiated so far.
    private Coordinate[] coordinatesOfPlayers;

    /**
     * Default constructor.
     */
    public TileGrid(){
        this.rows = 12;
        this.columns = 12;
        tileGrid = new Tile[rows][columns];
        this.players = new Player[1];
        this.coordinatesOfPlayers = new Coordinate[1];
        this.playersInitiated = 0;

        initiateTiles(tileGrid);
    }

    /**
     * Constructor with specifications.
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @param players The amount of players in the game.
     */
    public TileGrid(int rows, int columns, int players){
        this.rows = rows;
        this.columns = columns;
        tileGrid = new Tile[rows][columns];
        this.players = new Player[players];
        this.coordinatesOfPlayers = new Coordinate[players];
        this.playersInitiated = 0;

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
                            case CONVEYOR_NORTH:
                                tileGrid[row][column].addObjectOnTile(new ConveyorNorth());
                                break;
                            case PLAYER:
                                Player newPlayer = new Player();
                                tileGrid[row][column].addObjectOnTile(newPlayer);
                                players[playersInitiated] = newPlayer; // Add new player to list of players.
                                coordinatesOfPlayers[playersInitiated] = new Coordinate(row, column);
                                playersInitiated++; // One more player has been initiated, move the index 1 up.
                                break;

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

    public void movePlayer(int playerNumber, int rowsToMove, int columnsToMove){
        Player player = players[0];

        for(int i = 0; i<32; i++) {
            player.getSprite().translate(rowsToMove, columnsToMove);
        }

        Coordinate coordinatesOfPlayer = coordinatesOfPlayers[playerNumber];
        int rowOfPlayer = coordinatesOfPlayer.getRow();
        int columnOfPlayer = coordinatesOfPlayer.getColumn();


        tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(player);
        tileGrid[rowOfPlayer+rowsToMove][columnOfPlayer+columnsToMove].addObjectOnTile(player);
        coordinatesOfPlayers[playerNumber] = new Coordinate(rowOfPlayer+rowsToMove, columnOfPlayer+columnsToMove);
    }

    public Player getPlayer(int playerNumber){
        return players[playerNumber];
    }

    public Coordinate getCoordinatesOfPlayer(int playerNumber){
        return coordinatesOfPlayers[playerNumber];
    }

    private GameObjectType stringToGameObjectType(int nextTileTypeAsInt){
        switch(nextTileTypeAsInt){
            case 1: return GameObjectType.STANDARD_TILE;
            case 2: return GameObjectType.CONVEYOR_NORTH;
            case 3: return GameObjectType.PLAYER;
            default: return GameObjectType.STANDARD_TILE;
        }
    }

}

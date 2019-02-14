package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;

public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private final String fileName = "./assets/maplayout.txt";
    private Player[] players;
    private int playersInitiated; // How many players have been initiated so far.
    private Coordinate[] respawnPositions;
    private Coordinate[] coordinatesOfPlayers;

    /**
     * Default constructor.
     */
    public TileGrid(){
        this.rows = 12;
        this.columns = 12;
        tileGrid = new Tile[rows][columns];
        this.players = new Player[1];
        this.respawnPositions = new Coordinate[1];
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
        this.respawnPositions = new Coordinate[players];
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

    public Tile getTile(Coordinate coordinate){
        return tileGrid[coordinate.getRow()][coordinate.getColumn()];
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
                                Player newPlayer = new Player(playersInitiated);
                                tileGrid[row][column].addObjectOnTile(newPlayer);
                                players[playersInitiated] = newPlayer; // Add new player to list of players.
                                coordinatesOfPlayers[playersInitiated] = new Coordinate(row, column);
                                respawnPositions[playersInitiated] = new Coordinate(row, column);
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

    public void activateTiles(){
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                for (Player player : players) {
                    if (tile.hasPlayer(player)) {
                        System.out.println("Has player");
                        if(tile.hasConveyor()){
                            System.out.println("Has Conveyor");
                            ConveyorNorth conveyor = tile.getConveyor();
                            moveInDirectionOfConveyor(conveyor, player.getPlayerNumber());
                        }

                    }
                }
            }
        }
    }

    public void moveInDirectionOfConveyor(ConveyorNorth conveyor, int playerNumber){
        switch(conveyor.getOrientation()){
            case FACING_NORTH: movePlayer(playerNumber, 1, 0); break;
            case FACING_WEST: movePlayer(playerNumber, 0, -1); break;
            case FACING_SOUTH: movePlayer(playerNumber, -1, 0); break;
            case FACING_EAST: movePlayer(playerNumber, 0, 1); break;
            default: break;
        }
    }

    public void applyNextProgram(int playerNumber){
        Player player = players[playerNumber];
        ProgramCard nextProgramCard = player.getNextProgram();
        Program move = nextProgramCard.getMove();

        player.setCurrentMove(move);
    }

    /**
     *
     * @param move the rotation to be applied.
     * @param playerNumber the identifier of the player whose move should be continued.
     */
    public void applyRotation(Program move, int playerNumber){
        Player player = players[playerNumber];
        Orientation currentOrientation = player.getOrientation();
        player.updateOrientation(move);

    }

    /**
     * Method that makes a player perform a move.
     * @param move the move to apply
     * @param playerNumber the number of the player that the move should be applied to
     */
    public void applyMove(Program move, int playerNumber){
        Player player = players[playerNumber];

        int rowsToMove = 0;
        int columnsToMove = 0;
        if(move==Program.BACK){
            switch (player.getOrientation()) {
                case FACING_NORTH:
                    rowsToMove = -1;
                    break;
                case FACING_SOUTH:
                    rowsToMove = 1;
                    break;
                case FACING_WEST:
                    columnsToMove = 1;
                    break;
                case FACING_EAST:
                    columnsToMove = -1;
                    break;
            }
        }else {
            switch (player.getOrientation()) {
                case FACING_NORTH:
                    rowsToMove = 1;
                    break;
                case FACING_SOUTH:
                    rowsToMove = -1;
                    break;
                case FACING_WEST:
                    columnsToMove = -1;
                    break;
                case FACING_EAST:
                    columnsToMove = 1;
                    break;
            }
        }

        movePlayer(playerNumber, rowsToMove, columnsToMove);
    }

    /**
     * Method that continues the move a player has in progress.
     * @param playerNumber the identifier of the player whose move should be continued.
     */
    public void continueMove(int playerNumber){
        Player player = players[playerNumber];
        Program currentMove = player.getCurrentMove();
        int moveProgression = player.getMoveProgression();
        int totalMoves = currentMove.totalMoves();
        if(moveProgression == totalMoves){
            player.setCurrentMove(Program.NONE);
            player.resetMoveProgress();
        }else{
            boolean moveIsRotation = (currentMove==Program.LEFT) || (currentMove==Program.RIGHT) || (currentMove==Program.U);
            if(moveIsRotation){
                applyRotation(currentMove, playerNumber);
            }else{
                applyMove(currentMove, playerNumber);
            }
            player.progressMove();
        }
    }

    public void movePlayer(int playerNumber, int rowsToMove, int columnsToMove){

        Player player = players[playerNumber];
        Coordinate coordinatesOfPlayer = coordinatesOfPlayers[playerNumber];
        int rowOfPlayer = coordinatesOfPlayer.getRow();
        int columnOfPlayer = coordinatesOfPlayer.getColumn();

        if(!canMovePlayer(playerNumber, rowsToMove, columnsToMove, rowOfPlayer, columnOfPlayer)){
            return;
        }

        tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(player);
        tileGrid[rowOfPlayer+rowsToMove][columnOfPlayer+columnsToMove].addObjectOnTile(player);
        coordinatesOfPlayers[playerNumber] = new Coordinate(rowOfPlayer+rowsToMove, columnOfPlayer+columnsToMove);
    }

    private boolean canMovePlayer(int playerNumber, int rowsToMove, int columnsToMove, int rowOfPlayer, int columnOfPlayer){

        if((rowOfPlayer+rowsToMove > rows-1) || (rowOfPlayer+rowsToMove < 0)){
            playerOutOfBounds(playerNumber);
            return false;
        }
        if((columnOfPlayer+columnsToMove > columns-1) || (columnOfPlayer+columnsToMove < 0)){
            playerOutOfBounds(playerNumber);
            return false;
        }

        return true;
    }

    private void playerOutOfBounds(int playerNumber){
        // Respawn player
        respawnPlayer(playerNumber);
    }

    private void respawnPlayer(int playerNumber){
        Player player = players[playerNumber];

        int rowOfPlayer = coordinatesOfPlayers[playerNumber].getRow();
        int columnOfPlayer = coordinatesOfPlayers[playerNumber].getColumn();
        tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(player);

        int respawnRow = respawnPositions[playerNumber].getRow();
        int respawnColumn = respawnPositions[playerNumber].getColumn();
        tileGrid[respawnRow][respawnColumn].addObjectOnTile(player);
        coordinatesOfPlayers[playerNumber] = new Coordinate(respawnRow, respawnColumn);

        //players[playerNumber].getSprite().translate(respawnRow, respawnColumn);
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

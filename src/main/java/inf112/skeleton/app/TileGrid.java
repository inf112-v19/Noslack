package inf112.skeleton.app;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private String fileName = "./assets/maps/";
    private Player[] players;
    private int playersInitiated; // How many players have been initiated so far.

    /**
     * Default constructor.
     */
   /* public TileGrid(){
        this.fileName=this.fileName + "mapLayout.txt";

        this.rows = 12;
        this.columns = 12;
        this.tileGrid = new Tile[rows][columns];
        this.players = new Player[1];
        this.playersInitiated = 0;

        initiateTiles();
    }
    */

    /**
     * Constructor with specifications.
     * Uses standard map.
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @param players The amount of players in the game.
     */
    public TileGrid(int rows, int columns, int players){
        this.fileName = this.fileName + "ConveyorLoops.txt";
        this.rows = rows;
        this.columns = columns;
        this.tileGrid = new Tile[rows][columns];
        this.players = new Player[players];
        this.playersInitiated = 0;

        initiateTiles();
    }

    /**
     * Make sure the map to be used is filed under assets/maps/
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @param players The amount of players in the game.
     * @param file The file name, the program fixes directory.
     */
    public TileGrid(int rows, int columns, int players, String file) {
        this.fileName = this.fileName + file;
        this.rows = rows;
        this.columns = columns;
        this.tileGrid = new Tile[rows][columns];
        this.players = new Player[players];
        this.playersInitiated = 0;

        initiateTiles();
    }

    /**
     * Get Tile based on row and column
     * @param row: row of the requested tile
     * @param column: column of the requested tile
     * @return Tile at specified coordinate
     */
    public Tile getTile(int row, int column){
        return tileGrid[row][column];
    }

    /**
     * Get Tile based on coordinate
     * @param coordinate Coordinate of the requested Tile
     * @return Tile at specified coordinate
     */
    public Tile getTile(Coordinate coordinate){
        return tileGrid[coordinate.getRow()][coordinate.getColumn()];
    }

    /**
     * Initiates every tile in the grid
     * Todo: Implement reading tile-layout from file, or randomisation.
     */
    private void initiateTiles(){

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.fileName);
            // Designate the space used in the file between tiles
            String space =" ";
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for(int row = rows-1; row>=0; row--){
                String nextTileTypeLine = bufferedReader.readLine();
                String[] nextTileTypeLineArray = nextTileTypeLine.split(space);
                for(int column = columns-1; column>=0; column--) {
                    String nextTileTypesOfColumn = nextTileTypeLineArray[column];
                    tileGrid[row][column] = new Tile(GameObjectType.STANDARD_TILE);
                    String[] typesOnTile = nextTileTypesOfColumn.split(",");

                    for (String s : typesOnTile) {
                        // Adding objects on top of tile
                        if (!s.equals(space))  // If tile type is not standardTile
                            stringToGameObjectType(s, row, column);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param nextTileType String which contains the delegation of new type
     * @param row The row placement of the new tile
     * @param column The column of the new tile
     */

    private void stringToGameObjectType(String nextTileType, int row, int column) {
        Orientation orientation;
        switch (nextTileType.substring(0, 1)) {
            case "W":
                switch (nextTileType.charAt(nextTileType.length() - 1)) {
                    case '1':
                        orientation = Orientation.FACING_NORTH;
                        break;
                    case '2':
                        orientation = Orientation.FACING_EAST;
                        break;
                    case '3':
                        orientation = Orientation.FACING_SOUTH;
                        break;
                    case '4':
                        orientation = Orientation.FACING_WEST;
                        break;
                    default:
                        orientation = Orientation.FACING_NORTH;
                        break;
                }
                this.tileGrid[row][column].addObjectOnTile(new Wall(orientation));
                break;

            case "C":
                boolean fast = nextTileType.contains("CC");
                int rotating;
                if (nextTileType.contains("R"))
                    rotating = 1;
                else if (nextTileType.contains("L"))
                    rotating = -1;
                else
                    rotating = 0;
                switch (nextTileType.charAt(nextTileType.length() - 1)) {
                    case '1':
                        orientation = Orientation.FACING_NORTH;
                        break;
                    case '2':
                        orientation = Orientation.FACING_EAST;
                        break;
                    case '3':
                        orientation = Orientation.FACING_SOUTH;
                        break;
                    case '4':
                        orientation = Orientation.FACING_WEST;
                        break;
                    default:
                        orientation = Orientation.FACING_NORTH;
                        break;
                }
                this.tileGrid[row][column].addObjectOnTile(new Conveyor(orientation, fast, rotating));

                break;


            case "F":
                int n =(int) nextTileType.charAt(nextTileType.length()-1);
                this.tileGrid[row][column].addObjectOnTile(new Flag(n));
                break;

            case "H":
                this.tileGrid[row][column].addObjectOnTile(new Hole());
                break;
            case "R":
                this.tileGrid[row][column].addObjectOnTile(new RepairStation());
                break;
            case "P":
                Player newPlayer;

                switch (nextTileType.charAt(nextTileType.length() - 1)) {
                    case '1':
                        orientation = Orientation.FACING_NORTH;
                        break;
                    case '2':
                        orientation = Orientation.FACING_EAST;
                        break;
                    case '3':
                        orientation = Orientation.FACING_SOUTH;
                        break;
                    case '4':
                        orientation = Orientation.FACING_WEST;
                        break;

                    default:
                        orientation = Orientation.FACING_NORTH;
                        break;
                }


                newPlayer = new Player(playersInitiated, orientation);
                this.players[playersInitiated++] = newPlayer; // Add new player to list of players.
                this.tileGrid[row][column].addObjectOnTile(newPlayer);
                newPlayer.initiate(new Coordinate(row, column));
                break;

        }

    }

    /**
     * Runs trough the grid to find the players.
     * Then activates a function to find out what kind of tile the player is standing on.
     */
    public void activateTiles(){
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                for (Player player : players) {
                    if (tile.hasPlayer(player)) {
                        playerOnTile(tile, player);
                    }
                }
            }
        }
    }
    /**
     * Finds out what kind of tile the player is standing on and
     * if it has a function which effects the player.
     * @param tile The active file
     * @param player The active player
     */
    private void playerOnTile(Tile tile, Player player) {
        if(tile.hasConveyor()) {
            Conveyor conveyor = tile.getConveyor();
            moveInDirectionOfConveyor(conveyor, player.getPlayerNumber());
        }
        if(tile.hasRepairStation()){
            if (player.isFinished()) {
                player.repair();
                player.setBackUp(player.getPosition());
            }
        }
        if(tile.hasFlag()){
            if(player.isFinished()){
                player.setBackUp(player.getPosition());
                player.win();
            }
        }
        if(tile.hasHole()){
            respawnPlayer(player.getPlayerNumber());
        }
    }



    /**
     * Moves player on conveyor
     * @param conveyor Conveyor the player is on
     * @param playerNumber Players number
     */
    public void moveInDirectionOfConveyor(Conveyor conveyor, int playerNumber){
        if(getPlayer(playerNumber).getCurrentMove() == Program.NONE) {
            if(conveyor.getTurn() > 0){
                applyRotation(Program.RIGHT,playerNumber);
            }
            if(conveyor.getTurn() < 0){
                applyRotation(Program.LEFT,playerNumber);
            }
            if(conveyor.isFast()){
                switch (conveyor.getOrientation()) {
                    case FACING_NORTH:
                        movePlayer(playerNumber, 2, 0);
                        break;
                    case FACING_WEST:
                        movePlayer(playerNumber, 0, -2);
                        break;
                    case FACING_SOUTH:
                        movePlayer(playerNumber, -2, 0);
                        break;
                    case FACING_EAST:
                        movePlayer(playerNumber, 0, 2);
                        break;
                    default:
                        break;
                }
            }
            else{
                switch (conveyor.getOrientation()) {
                    case FACING_NORTH:
                        movePlayer(playerNumber, 1, 0);
                        break;
                    case FACING_WEST:
                        movePlayer(playerNumber, 0, -1);
                        break;
                    case FACING_SOUTH:
                        movePlayer(playerNumber, -1, 0);
                        break;
                    case FACING_EAST:
                        movePlayer(playerNumber, 0, 1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Apply the next program in the players queue.
     * @param playerNumber Player number
     */
    public void applyNextProgram(int playerNumber){
        getPlayer(playerNumber).setCurrentMove(getPlayer(playerNumber).getNextProgram().getMove());
    }

    /**
     *Apply rotation to a player
     * @param move the rotation to be applied.
     * @param playerNumber the identifier of the player whose move should be continued.
     */
    public void applyRotation(Program move, int playerNumber){
        getPlayer(playerNumber).updateOrientation(move);
    }

    /**
     * Method that makes a player perform a move.
     * @param move the move to apply
     * @param playerNumber the number of the player that the move should be applied to
     */
    public void applyMove(Program move, int playerNumber){
        int rowsToMove = 0;
        int columnsToMove = 0;

        switch (this.players[playerNumber].getOrientation()) {
            case FACING_NORTH:
                rowsToMove = 1;
                break;
            case FACING_SOUTH:
                rowsToMove = -1;
                break;
            case FACING_EAST:
                columnsToMove = 1;
                break;
            case FACING_WEST:
                columnsToMove = -1;
                break;
        }
        if(move==Program.BACK){
            rowsToMove *= -1;
            columnsToMove *= -1;
        }
        movePlayer(playerNumber, rowsToMove, columnsToMove);
    }

    /**
     * Method that continues the move a player has in progress.
     * @param playerNumber the identifier of the player whose move should be continued.
     */
    public void continueMove(int playerNumber){
        Program currentMove = getPlayer(playerNumber).getCurrentMove();
        int moveProgression = getPlayer(playerNumber).getMoveProgression();
        int totalMoves = currentMove.totalMoves();

        if(moveProgression == totalMoves){
            getPlayer(playerNumber).setCurrentMove(Program.NONE);
            getPlayer(playerNumber).resetMoveProgress();
        }else{
            boolean moveIsRotation = (currentMove==Program.LEFT) || (currentMove==Program.RIGHT) || (currentMove==Program.U);
            if(moveIsRotation){
                applyRotation(currentMove, playerNumber);
            }else{
                applyMove(currentMove, playerNumber);
            }
            getPlayer(playerNumber).progressMove();
        }
    }

    /**
     * Move the player
     * @param playerNumber Player number
     * @param rowsToMove Rows the Player is to move
     * @param columnsToMove Columns the Player is to move
     */
    public void movePlayer(int playerNumber, int rowsToMove, int columnsToMove){
        int rowOfPlayer = getPlayerPosition(playerNumber).getRow();
        int columnOfPlayer = getPlayerPosition(playerNumber).getColumn();

        if(!canMovePlayer(playerNumber, rowsToMove, columnsToMove)) return;

        this.tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(getPlayer(playerNumber));
        this.tileGrid[rowOfPlayer+rowsToMove][columnOfPlayer+columnsToMove].addObjectOnTile(getPlayer(playerNumber));
        getPlayer(playerNumber).setPosition(new Coordinate(rowOfPlayer+rowsToMove, columnOfPlayer+columnsToMove));
    }

    /**
     * TODO Simplify the if statements
     * Checks if the player can move.
     * @param playerNumber Players number
     * @param rowsToMove How many rows the player needs to move
     * @param columnsToMove How many columns player needs to move
     * @return Boolean for if the player can move.
     */
    private boolean canMovePlayer(int playerNumber, int rowsToMove, int columnsToMove){
        Coordinate coordinateOfPlayer = getPlayerPosition(playerNumber);
        if((coordinateOfPlayer.getRow()+rowsToMove > this.rows-1) || (coordinateOfPlayer.getRow()+rowsToMove < 0)){
            //Player out of bounds
            respawnPlayer(playerNumber);
            return false;
        }
        if((coordinateOfPlayer.getColumn()+columnsToMove > this.columns-1) ||
                (coordinateOfPlayer.getColumn()+columnsToMove < 0)){
            //Player out of bounds
            respawnPlayer(playerNumber);
            return false;
        }
        return true;
    }

    /**
     * Respawns the player after it has fallen out of grid, with health=6.
     * @param playerNumber Players number
     */
    private void respawnPlayer(int playerNumber){

        int rowOfPlayer = getPlayerPosition(playerNumber).getRow();
        int columnOfPlayer = getPlayerPosition(playerNumber).getColumn();
        this.tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(getPlayer(playerNumber));

        int respawnRow = getPlayer(playerNumber).getBackUp().getRow();
        int respawnColumn = getPlayer(playerNumber).getBackUp().getColumn();

        Player p = getPlayer(playerNumber);
        this.tileGrid[respawnRow][respawnColumn].addObjectOnTile(p);
        p.reset();
        p.setPosition(new Coordinate(respawnRow, respawnColumn));



        //players[playerNumber].getSprite().translate(respawnRow, respawnColumn);
    }

    /**
     * Get Player
     * @param playerNumber Player number
     * @return Wanted Player
     */
    public Player getPlayer(int playerNumber){
        return this.players[playerNumber];
    }

    /**
     * Get Players Coordinates
     * @param playerNumber Player number
     * @return Players current position
     */
    public Coordinate getPlayerPosition(int playerNumber){
        return this.players[playerNumber].getPosition();
    }

    /**
     * Get Players health
     * @param playerNumber The player number
     * @return Players Heath
     */
    public int getPlayerHealth(int playerNumber){
        return this.players[playerNumber].getHealth();
    }

    /**
     * Get Players ProgramHand
     * @param playerNumber Players number
     * @return Player ProgramHand
     */
    public ArrayList<ProgramCard> getPlayerProgramHand(int playerNumber){
        return this.players[playerNumber].getProgramHand();
    }

    /**
     * Get players current move
     * @param playerNumber Player Number
     * @return Program of current move
     */
    public Program getPlayerCurrentMove (int playerNumber){
        return this.players[playerNumber].getCurrentMove();
    }

    /**
     * Reset a player
     * @param playerNumber Player number
     */
    public void resetPlayer(int playerNumber){
        this.players[playerNumber].reset();
    }


}
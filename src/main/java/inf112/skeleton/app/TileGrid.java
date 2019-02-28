package inf112.skeleton.app;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private  String fileName ="./assets/maps/";
    private Player[] players;
    private int playersInitiated; // How many players have been initiated so far.

    /**
     * Default constructor.
     */
    public TileGrid(){
        this.fileName=this.fileName + "mapLayout.txt";
        this.rows = 12;
        this.columns = 12;
        this.tileGrid = new Tile[rows][columns];
        this.players = new Player[1];
        this.playersInitiated = 0;

        initiateTiles();
    }

    /**
     * Constructor with specifications.
     * Uses standard map.
     * @param rows The amount of rows in the grid.
     * @param columns The amount of columns in the grid.
     * @param players The amount of players in the game.
     */
    public TileGrid(int rows, int columns, int players){
        this.fileName = this.fileName + "mapLayout.txt";
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
     * Get Til
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
     * Todo: Implement reading tile-layout from file, or randomisation.
     */
    private void initiateTiles(){

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);
            String space =" ";
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            for(int row = rows-1; row>=0; row--){
                String nextTileTypeLine = bufferedReader.readLine();
                String[] nextTileTypeLineArray = nextTileTypeLine.split(" ");
                for(int column = columns-1; column>=0; column--){

                    String nextTileTypesOfColumn = nextTileTypeLineArray[column];
                    tileGrid[row][column] = new Tile(GameObjectType.STANDARD_TILE);
                    String[] typesOnTile = nextTileTypesOfColumn.split(",");

                    for(int index = 0; index<typesOnTile.length; index++) {


                        GameObjectType nextTileType = stringToGameObjectType(typesOnTile[index]);

                        // Adding objects on top of tile
                        if (typesOnTile[index] != space) { // If tile type is not standardTile
                            switch (nextTileType) {
                                case FLAG:
                                    tileGrid[row][column].addObjectOnTile(new Flag());
                                    break;
                                case CONVEYOR_NORTH:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor());
                                    break;
                                case CONVEYOR_EAST:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_EAST));
                                    break;
                                case CONVEYOR_WEST:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_WEST));
                                    break;
                                case CONVEYOR_SOUTH:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_SOUTH));
                                    break;
                                case FAST_CONVEYOR_NORTH:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(true));
                                    break;
                                case FAST_CONVEYOR_EAST:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_EAST, true));
                                    break;
                                case FAST_CONVEYOR_WEST:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_WEST,true));
                                    break;
                                case FAST_CONVEYOR_SOUTH:
                                    tileGrid[row][column].addObjectOnTile(new Conveyor(Orientation.FACING_SOUTH,true));
                                    break;
                                case PLAYER_NORTH:
                                    Player newPlayer = new Player(playersInitiated);
                                    tileGrid[row][column].addObjectOnTile(newPlayer);
                                    players[playersInitiated] = newPlayer; // Add new player to list of players.
                                    newPlayer.setPosition(new Coordinate(row, column));
                                    newPlayer.setBackUp(new Coordinate(row, column));
                                    playersInitiated++; // One more player has been initiated, move the index 1 up.
                                    break;
                                case NORTH_WALL:
                                    tileGrid[row][column].addObjectOnTile(new Wall(Orientation.FACING_NORTH));
                                    break;
                                case WEST_WALL:
                                    tileGrid[row][column].addObjectOnTile(new Wall(Orientation.FACING_WEST));
                                    break;
                                case SOUTH_WALL:
                                    tileGrid[row][column].addObjectOnTile(new Wall(Orientation.FACING_SOUTH));
                                    break;
                                case EAST_WALL:
                                    tileGrid[row][column].addObjectOnTile(new Wall(Orientation.FACING_EAST));
                                    break;
                                case PLAYER:
                            }

                        }
                    }

                }
            }

            bufferedReader.close();
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + this.fileName + "'");
        }catch(IOException ex) {
            System.out.println("Error reading file '" + this.fileName + "'");
        }
    }

    private GameObjectType charToGameObjectType(char nextTileType){
        switch(nextTileType){
            case '1': return GameObjectType.STANDARD_TILE;
            case '2': return GameObjectType.CONVEYOR_NORTH;
            case '3': return GameObjectType.PLAYER;
            case '4': return GameObjectType.FLAG;

            case 'w': return GameObjectType.NORTH_WALL;
            case 'a': return GameObjectType.WEST_WALL;
            case 's': return GameObjectType.SOUTH_WALL;
            case 'd': return GameObjectType.EAST_WALL;

            default: return GameObjectType.STANDARD_TILE;
        }
    }

    /**
     * Runs trough the grid to find the players.
     * Then avtivates a function to find out what kind of tile the player is standing on.
     */
    public void activateTiles(){
        for(Tile[] tileRow : this.tileGrid)
            for(Tile tile : tileRow)
                for (Player player : this.players)
                    if (tile.hasPlayer(player))
                        playerOnTile(tile,player);
    }

    /**
     * Finds out what kind of tile the player is standing on and
     * if it has a function which effects the player.
     * @param tile The active file
     * @param player The active player
     */
    private void playerOnTile(Tile tile, Player player){
        if(tile.hasConveyor()) {
            Conveyor conveyor = tile.getConveyor();
            moveInDirectionOfConveyor(conveyor, player.getPlayerNumber());
        }
        if(tile.hasRepairStation()){
            player.repair();
            player.setBackUp(player.getPosition());
        }
        if(tile.hasFlag()) {
            if (player.isFinished())
                player.win();
        }
    }

    public void moveInDirectionOfConveyor(Conveyor conveyor, int playerNumber){
        if(getPlayer(playerNumber).getCurrentMove() == Program.NONE) {
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

    public void applyNextProgram(int playerNumber){
        /*
        Player player = this.players[playerNumber];
        ProgramCard nextProgramCard = player.getNextProgram();
        Program move = nextProgramCard.getMove();

        player.setCurrentMove(move);
        */
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
        Coordinate coordinatesOfPlayer = getPlayer(playerNumber).getPosition();
        int rowOfPlayer = coordinatesOfPlayer.getRow();
        int columnOfPlayer = coordinatesOfPlayer.getColumn();

        if(!canMovePlayer(playerNumber, rowsToMove, columnsToMove, coordinatesOfPlayer)){
            return;
        }

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
     * @param coordinateOfPlayer Player coordinate
     * @return Boolean for if the player can move.
     */
    private boolean canMovePlayer(int playerNumber, int rowsToMove, int columnsToMove, Coordinate coordinateOfPlayer){

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

        if(playerFacingWall(playerNumber, coordinateOfPlayer.getRow(), coordinateOfPlayer.getColumn())){
            return false;
        }

        return true;
    }

    private boolean playerFacingWall(int playerNumber, int rowOfPlayer, int columnOfPlayer){
        switch(getPlayer(playerNumber).getOrientation()){
            case FACING_NORTH:
                Tile tileNorthOfPlayer = tileGrid[rowOfPlayer-1][columnOfPlayer];
                if(tileNorthOfPlayer.hasWallWithOrientation(Orientation.FACING_SOUTH)){
                    return true;
                }
                break;
            case FACING_WEST:
                Tile tileWestOfPlayer = tileGrid[rowOfPlayer][columnOfPlayer-1];
                if(tileWestOfPlayer.hasWallWithOrientation(Orientation.FACING_EAST)){
                    return true;
                }
                break;
            case FACING_SOUTH:
                Tile tileSouthOfPlayer = tileGrid[rowOfPlayer+1][columnOfPlayer];
                if(tileSouthOfPlayer.hasWallWithOrientation(Orientation.FACING_NORTH)){
                    return true;
                }
                break;
            case FACING_EAST:
                Tile tileEastOfPlayer = tileGrid[rowOfPlayer][columnOfPlayer+1];
                if(tileEastOfPlayer.hasWallWithOrientation(Orientation.FACING_WEST)){
                    return true;
                }
                break;
        }

        return false;
    }

    /**
     * Respawns the player after it has fallen out of grid, with health=6.
     * @param playerNumber Players number
     */
    private void respawnPlayer(int playerNumber){
        //Player player = this.players[playerNumber];

        int rowOfPlayer = getPlayer(playerNumber).getPosition().getRow();
        int columnOfPlayer = getPlayer(playerNumber).getPosition().getColumn();
        this.tileGrid[rowOfPlayer][columnOfPlayer].removeObjectFromTile(getPlayer(playerNumber));

        int respawnRow = getPlayer(playerNumber).getBackUp().getRow();
        int respawnColumn = getPlayer(playerNumber).getBackUp().getColumn();

        tileGrid[respawnRow][respawnColumn].addObjectOnTile(getPlayer(playerNumber));
        getPlayer(playerNumber).setPosition(new Coordinate(respawnRow, respawnColumn));
        getPlayer(playerNumber).recieveDamage(3);

        //players[playerNumber].getSprite().translate(respawnRow, respawnColumn);
    }

    public Player getPlayer(int playerNumber){
        return this.players[playerNumber];
    }

    public Coordinate getCoordinatesOfPlayer(int playerNumber){
        return this.players[playerNumber].getPosition();
    }

    private GameObjectType stringToGameObjectType(String nextTileType){
        switch(nextTileType){
            case "1": return GameObjectType.STANDARD_TILE;
            case "C1": return GameObjectType.CONVEYOR_NORTH;
            case "C2": return GameObjectType.CONVEYOR_EAST;
            case "C3": return GameObjectType.CONVEYOR_SOUTH;
            case "C4": return GameObjectType.CONVEYOR_WEST;
            case "CC1": return GameObjectType.FAST_CONVEYOR_NORTH;
            case "CC2": return GameObjectType.FAST_CONVEYOR_EAST;
            case "CC3": return GameObjectType.FAST_CONVEYOR_SOUTH;
            case "CC4": return GameObjectType.FAST_CONVEYOR_WEST;

            //Player
            case "P1": return GameObjectType.PLAYER_NORTH;
            case "P2": return GameObjectType.PLAYER_EAST;
            case "P3": return GameObjectType.PLAYER_SOUTH;
            case "P4": return GameObjectType.PLAYER_WEST;


            //Default(non orientation spesific) objects needs to be down here so they don't trigger before
            case "CC": return GameObjectType.FAST_CONVEYOR_NORTH;
            case "C": return GameObjectType.CONVEYOR_NORTH;
            case "F": return GameObjectType.FLAG;
            case "P": return GameObjectType.PLAYER_NORTH;

            default: return GameObjectType.STANDARD_TILE;
        }
    }

}

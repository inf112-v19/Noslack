package inf112.skeleton.app;

import inf112.skeleton.app.cards.Ability;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.*;
import inf112.skeleton.app.gameobjects.Robots.*;
import inf112.skeleton.app.gameobjects.tiletypes.*;

import java.util.ArrayList;


public class TileGrid{
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private ArrayList<IRobot> players;
    private int flagsInitiated; // How many flags have been initiated so far.(So that you only win when you reach the last one)
    private int playersInitiated; // How many players have been initiated so far.

    /**
     * Constructor with specifications.
     * Uses standard map.
     */
    public TileGrid(){
        build("ConveyorLoops.txt");
    }

    /**
     * Make sure the map to be used is filed under assets/maps/
     * @param file The file name, the program fixes directory.
     */
    public TileGrid(String file) {
        build(file);
    }

    /**
     * Constructs tilegrid and declares initial values.
     * @param file name of file
     */
    private void build(String file) {
        TileGridBuilder builder= new TileGridBuilder(file);
        this.tileGrid = builder.getTileGrid();
        this.players = builder.getPlayers();
        this.rows = builder.getRows();
        this.columns = builder.getColumns();
        this.playersInitiated = builder.getPlayersInitiated();
        this.flagsInitiated = builder.getFlagsInitiated();
    }

    /**
     * Get the number of rows in TileGrid
     * @return Rows in grid
     */
    int getRows() {
        return rows;
    }

    /**
     * Get the number of columns in TileGrid
     * @return Columns in grid
     */
    int getColumns() {
        return columns;
    }

    /**
     * Get the number of flags in TileGrid
     * @return Flags in grid
     */
    int getFlagsInitiated(){
        return flagsInitiated;
    }

    /**
     * Get the number of players in TileGrid
     * @return Players in grid
     */
    int getPlayersInitiated(){
        return playersInitiated;
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
     * Runs trough the grid to find the players.
     * Then activates a function to find out what kind of tile the player is standing on.
     */

    public void activateTiles(){
        activateTiles(0);
    }

    public void activateTiles(int currentPhase){
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                for (IRobot player : players) {
                    if (tile.hasPlayer(player) && !player.hasMoved()) {
                        playerOnTile(tile, player, currentPhase);
                    }
                }
            }
        }
        for(IRobot p : players){
            p.moved(false);
        }
    }



    /**
     * Finds out what kind of tile the player is standing on and
     * if it has a function which effects the player.
     * @param tile The active file
     * @param player The active player
     */
    private void playerOnTile(Tile tile, IRobot player, int currentPhase) {
        // Conveyor
        if(tile.hasGameObject(GameObjectType.CONVEYOR)) {
            Conveyor conveyor = (Conveyor) tile.getGameObject(GameObjectType.CONVEYOR);
            moveInDirectionOfConveyor(conveyor, player.getPlayerNumber());
        }
        // Repair Station
        if(tile.hasGameObject(GameObjectType.REPAIR_STATION)){
            if (player.isFinished()) {
                player.repair();
                player.setBackUp();
            }
        }
        // Flag
        if(tile.hasGameObject(GameObjectType.FLAG)){
            if(player.isFinished()){
                int n = ((Flag)tile.getGameObject(GameObjectType.FLAG)).getFlagNumber();
                //Adds flag to flagsVisited only if it has visited all previous flags.
                if(n==1||player.getFlag(n-1)){
                    //Creates a backUp
                    player.setBackUp();
                    if(!player.getFlag(n)) {
                        player.visitFlag(n);
                    }
                    System.out.println("Visited: " + player.getFlagsVisited().toString());

                    //if you are on the last flag, and visited all previous, you win.
                    if (n >= flagsInitiated) {
                        player.win();
                    }
                }
            }
        }
        //Teleporter
        if(tile.hasGameObject(GameObjectType.TELEPORTER)&& (player.getMoveProgression() == 0)){
            Teleporter teleporter = (Teleporter)tile.getGameObject(GameObjectType.TELEPORTER);
            tile.removeObjectFromTile(player);
            getTile(teleporter.getTeleportLocation()).addObjectOnTile(player);
            setPlayerPosition(player.getPlayerNumber(),teleporter.getTeleportLocation());
        }
        // Hole
        if(tile.hasGameObject(GameObjectType.HOLE)){
            respawnPlayer(player.getPlayerNumber());
        }
        // Laser
        if(tile.hasGameObject(GameObjectType.LASER_BEAM)){
            laserDamagePlayer(((LaserBeam)tile.getGameObject(GameObjectType.LASER_BEAM)).isDual(),player);
        }
        if(tile.hasGameObject(GameObjectType.LASER_OUTLET)){
            laserDamagePlayer(((LaserOutlet)tile.getGameObject(GameObjectType.LASER_OUTLET)).isDual(),player);
        }
        // Rotator activation
        if(tile.hasGameObject(GameObjectType.ROTATOR_CLOCKWISE)){
            applyRotation(Program.RIGHT, player.getPlayerNumber());
        }
        if(tile.hasGameObject(GameObjectType.ROTATOR_COUNTER_CLOCKWISE)){
            applyRotation(Program.LEFT, player.getPlayerNumber());
        }
        // Pusher activation
        if(tile.hasGameObject(GameObjectType.PUSHER)){
            Pusher pusher = (Pusher) tile.getGameObject(GameObjectType.PUSHER);
            Orientation orientation = pusher.getOrientation();

            int n[] = calculateMove(orientation);
            if((currentPhase+1)%2==0 && pusher.isEven()){
                movePlayer(player.getPlayerNumber(),n[0],n[1]);
            }
            else if((currentPhase+1)%2 != 0 && !pusher.isEven()){
                movePlayer(player.getPlayerNumber(),n[0],n[1]);
            }
            int[] move = calculateMove(pusher.getOrientation());
            movePlayer(player.getPlayerNumber(), move[0],move[1]);
        }
    }
    /**
     * Damage a player by laser
     * @param dual Is the laser Dual
     * @param player the player to be damaged
     */
    private void laserDamagePlayer(boolean dual, IRobot player){
        if (dual) {
            if (player.receiveDamage(2)) respawnPlayer(player);
        }
        else {
            if (player.receiveDamage()) respawnPlayer(player);
        }
    }
    /**
     * Moves player on conveyor
     * @param conveyor Conveyor the player is on
     * @param playerNumber Players number
     */
    private void moveInDirectionOfConveyor(Conveyor conveyor, int playerNumber){
        IRobot player = getPlayer(playerNumber);
        if(conveyor.getTurn() > 0){
            applyRotation(Program.RIGHT,playerNumber);
        }
        if(conveyor.getTurn() < 0){
            applyRotation(Program.LEFT,playerNumber);
        }
        int[] move = calculateMove(conveyor.getOrientation());
        int rowsToMove = move[0];
        int colsToMove = move[1];

        Coordinate nextTile = player.getPosition().moveCoordinate(rowsToMove,colsToMove);

        movePlayer(playerNumber,rowsToMove,colsToMove);
        if(conveyor.isFast() && !player.hasMoved()){
            player.moved(true);
            try{
                if(getTile(nextTile).hasGameObject(GameObjectType.CONVEYOR)){
                    Conveyor nextConveyor = (Conveyor) getTile(nextTile).getGameObject(GameObjectType.CONVEYOR);
                    moveInDirectionOfConveyor(nextConveyor,playerNumber);
                }
            }catch(ArrayIndexOutOfBoundsException e){

            }
        }
    }

    /**
     * Calculate new move, based on orientation
     * @param orientation Orientation of movement
     * @return a list of integers, first row, second column
     */
    private int[] calculateMove(Orientation orientation){
        int[] rowColumn = new int[2];
        switch (orientation) {
            case FACING_NORTH:
                rowColumn[0] = 1;
                break;
            case FACING_WEST:
                rowColumn[1] = -1;
                break;
            case FACING_SOUTH:
                rowColumn[0] = -1;
                break;
            case FACING_EAST:
                rowColumn[1] = 1;
                break;
        }
        return rowColumn;
    }

    /**
     * Apply the next program in the players queue.
     * @param playerNumber Player number
     */
    void applyNextProgram(int playerNumber){
        getPlayer(playerNumber).setNextProgram();
    }

    /**
     *Apply rotation to a player
     * @param move the rotation to be applied.
     * @param playerNumber the identifier of the player whose move should be continued.
     */
    private void applyRotation(Program move, int playerNumber){
        getPlayer(playerNumber).updateOrientation(move);
    }

    /**
     * Method that makes a player perform a move.
     * @param move the move to apply
     * @param playerNumber the number of the player that the move should be applied to
     */
    private void applyMove(Program move, int playerNumber){
        int[] movement = calculateMove(getPlayer(0).getOrientation());
        int rowsToMove = movement[0];
        int columnsToMove = movement[1];

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

        if(moveProgression==currentMove.totalMoves()){
            getPlayer(playerNumber).setCurrentMove(Program.NONE);
            getPlayer(playerNumber).resetMoveProgress();
        }
        else{
            if(!currentMove.isMove()){
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
        setPlayerPosition(playerNumber, getPlayer(playerNumber).getPosition().moveCoordinate(rowsToMove,columnsToMove));
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
        Orientation directionOfMove = findOrientationOfMovement(rowsToMove, columnsToMove);
        if(playerBlockedOnCurrentTile(getPlayer(playerNumber), directionOfMove)){
            return false;
        }
        if(playerOutOfBounds(coordinateOfPlayer,rowsToMove,columnsToMove)){
            respawnPlayer(playerNumber);
            return false;
        }
        if(playerBlockedOnNextTile(getPlayer(playerNumber), directionOfMove,rowsToMove,columnsToMove)){
            return false;
        }
        return true;
    }

    /**
     * Finds the Orientation of the move about to be applied
     * @param rowsToMove Rows to be moved
     * @param columnsToMove Columns to be moved.
     * @return The orientation of the movement.
     */
    private Orientation findOrientationOfMovement(int rowsToMove, int columnsToMove){
        if(columnsToMove==0){
            if(rowsToMove>0)
                return Orientation.FACING_NORTH;
            if(rowsToMove<0)
                return Orientation.FACING_SOUTH;
        }
        if(rowsToMove==0){
            if(columnsToMove>0)
                return Orientation.FACING_EAST;
            if(columnsToMove<0)
                return Orientation.FACING_WEST;
        }
        return null;
    }

    /**
     * @param player Player in question
     * @return If the path is blocked on the Tile
     */
    private boolean playerBlockedOnCurrentTile(IRobot player, Orientation directionOfMove){
        Tile tile = getTile(player.getPosition());
        return tile.playerPathBlocked(player, directionOfMove);
    }
    /**
     * @param player Active player
     * @param rowsToMove Rows to where the player is moving
     * @param columnsToMove Columns to where the player is moving
     * @return If player can move, or is blocked by wall
     */
    private boolean playerBlockedOnNextTile(IRobot player, Orientation directionOfMove, int rowsToMove, int columnsToMove){
        Coordinate coordinate = player.getPosition().moveCoordinate(rowsToMove,columnsToMove);
        if(getTile(coordinate).hasGameObject(GameObjectType.ROBOT)){
            Player otherPlayer = (Player)getTile(coordinate).getGameObject(GameObjectType.ROBOT);
            int[] move= calculateMove(directionOfMove);
            movePlayer(otherPlayer.getPlayerNumber(),move[0],move[1]);
        }


        return getTile(coordinate).playerPathBlocked(player, directionOfMove);
    }

    /**
     * Finds out if the player is moving out of bounds
     * @param coordinateOfPlayer Current position of player in question
     * @param rowsToMove The number of rows to be moved
     * @param columnsToMove The number of columns to be moved
     * @return Is the move out of bounds.
     */
    private boolean playerOutOfBounds(Coordinate coordinateOfPlayer, int rowsToMove, int columnsToMove){
        Coordinate newCoordinate = coordinateOfPlayer.moveCoordinate(rowsToMove,columnsToMove);

        if(newCoordinate.getRow() > this.rows-1 || newCoordinate.getRow() < 0){
            return true;
        }
        if(newCoordinate.getColumn() > this.columns-1 || newCoordinate.getColumn() < 0){
            return true;
        }
        if(getTile(newCoordinate).hasGameObject(GameObjectType.HOLE)){
            return true;
        }

        return false;
    }

    /**
     * Get Player
     * @param playerNumber Player number
     * @return Wanted Player
     */
    public IRobot getPlayer(int playerNumber){
        return this.players.get(playerNumber);
    }

    /**
     * Get all players
     * @return List of players
     */
    ArrayList<IRobot> getPlayers() {
        return players;
    }

    /**
     * Reset a player
     * @param playerNumber Player number
     */
    void resetPlayer(int playerNumber){
        getPlayer(playerNumber).reset();
    }

    /**
     * Respawns the player after it has fallen out of grid, with health=6.
     * @param playerNumber Players number
     */
    private void respawnPlayer(int playerNumber){
        respawnPlayer(getPlayer(playerNumber));
    }
    /**
     * Respawns the player after it has fallen out of grid, with health=6.
     * @param player The Players
     */
    private void respawnPlayer(IRobot player){
        getTile(player.getPosition()).removeObjectFromTile(player);
        player.respawn();
        getTile(player.getBackUp()).addObjectOnTile(player);
    }

    /**
     * Get Players Coordinates
     * @param playerNumber Player number
     * @return Players current position
     */
    public Coordinate getPlayerPosition(int playerNumber){
        return getPlayer(playerNumber).getPosition();
    }

    /**
     * Get Players ProgramHand
     * @param playerNumber Players number
     * @return Player ProgramHand
     */
    ArrayList<ProgramCard> getPlayerProgramHand(int playerNumber){
        return getPlayer(playerNumber).getProgramHand();
    }

    /**
     * Get players current move
     * @param playerNumber Player Number
     * @return Program of current move
     */
    Program getPlayerCurrentMove(int playerNumber){
        return getPlayer(playerNumber).getCurrentMove();
    }

    /**
     * Get the players ability
     * @param playerNumber The players number
     * @return The players ability
     */
    boolean playerHasAbility(int playerNumber, Ability ability){
        return getPlayer(playerNumber).hasAbility(ability);
    }

    /**
     * Set new position of player
     * @param playerNumber player number
     * @param coordinate New Coordinate
     */
    private void setPlayerPosition(int playerNumber, Coordinate coordinate){
        getPlayer(playerNumber).setPosition(coordinate);
    }

    public void fireControl(int playerNumber) {
        Coordinate position = getPlayerPosition(playerNumber);
        if(playerHasAbility(playerNumber, Ability.PressorBeam)) {
            int playerToMove = playerInLine(playerNumber);
            int[] movement=calculateMove(position.getOrientation());
            if(playerToMove!=playerNumber) {
                if(continueBeam(getPlayer(playerNumber).getPosition(), getPlayer(playerToMove).getPosition())) {
                    movePlayer(playerToMove, movement[0], movement[1]);
                }
            }
        } else if(playerHasAbility(playerNumber, Ability.TractorBeam)) {
            int playerToMove = playerInLine(playerNumber);
            int[] movement=calculateMove(position.getOrientation().opposite());
            if(playerToMove!=playerNumber) {
                if(continueBeam(getPlayer(playerNumber).getPosition(), getPlayer(playerToMove).getPosition())) {
                    movePlayer(playerToMove, movement[0], movement[1]);
                }
            }
        } else {
            firePlayerLaser(playerNumber);
        }
    }

    public boolean continueBeam(Coordinate position, Coordinate opponnentPos) {

        while (!position.equals(opponnentPos)) {
            if(!tileCheck(position, false)){
                return false;
            }
            position = position.moveCoordinate();
        }
        return true;
    }

    /**
     * Adds laser to board if player wants to fire.
     * @param playerNumber player who is to fire laser
     */
    public void firePlayerLaser(int playerNumber) {
        Coordinate position = getPlayerPosition(playerNumber);
        position.setOrientation(getPlayer(playerNumber).getOrientation());
        Orientation laserOrientation = position.getOrientation().laserOrientation();
        boolean dual = playerHasAbility(0, Ability.DoubleBarreledLaser);

        boolean firing = continueFiring(position);
        while (firing) {
            position = position.moveCoordinate();
            getTile(position).addObjectOnTile(new LaserBeam(laserOrientation,dual, playerNumber));
            firing = continueFiring(position);
        }
        if(playerHasAbility(playerNumber, Ability.RearFiringLaser)){
            position = getPlayerPosition(playerNumber);
            position.setOrientation(getPlayer(playerNumber).getOrientation().opposite());

            firing = continueFiring(position);
            while (firing) {
                position = position.moveCoordinate();
                getTile(position).addObjectOnTile(new LaserBeam(laserOrientation,dual, playerNumber));
                firing = continueFiring(position);
            }
        }


    }



    /**
     * Figures out if the laser can keep firing
     * @param position lasers current position
     * @return If hte laser can keep firing.
     */
    private boolean continueFiring(Coordinate position){
        if(tileCheck(position,false)) {
            return false;
        }
        position = position.moveCoordinate();

        if(tileCheck(position,true)){
            return false;
        }
        return true;
    }

    /**
     * Checks if it is possible to add a laser on the Tile
     * @param position Position of the tile
     * @param nextTile True if it is concerning the next tile over
     * @return If it's not possible for the laser to be placed on this tile.
     */
    private boolean tileCheck(Coordinate position, boolean nextTile){
        if(position.getRow()<0 || position.getRow()>this.rows-1 ||
                position.getColumn()<0 || position.getColumn()>this.columns-1){
            return true;
        }
        Tile tile = getTile(position);
        if(nextTile){
            if(tile.orientationBlocked(position.getOrientation().opposite())){
                return true;
            }
        }
        else{
            if(tile.orientationBlocked(position.getOrientation())){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the players fired laser
     * @param playerNumber playerNumber
     */
    public void removePlayerLaser(int playerNumber){
        Coordinate position = getPlayerPosition(playerNumber);
        position.setOrientation(getPlayer(playerNumber).getOrientation());

        boolean firing = continueFiring(position);
        while (firing) {
            position = position.moveCoordinate();
            getTile(position).removePlayerLaserFromTile(playerNumber);
            firing = continueFiring(position);
        }

        if(playerHasAbility(playerNumber, Ability.RearFiringLaser)){
            position = getPlayerPosition(playerNumber);
            position.setOrientation(getPlayer(playerNumber).getOrientation().opposite());

            firing = continueFiring(position);
            while (firing) {
                position = position.moveCoordinate();
                getTile(position).removePlayerLaserFromTile(playerNumber);
                firing = continueFiring(position);
            }
        }
    }

    public int playerInLine(int playerNumber) {

        int column = getPlayer(playerNumber).getPosition().getColumn();
        int row = getPlayer(playerNumber).getPosition().getRow();
        int playerInLine = playerNumber;

        for(IRobot p : players) {
            switch (getPlayer(playerNumber).getOrientation()) {
                case FACING_NORTH:
                    if(p.getPosition().getRow()==row && p.getPosition().getColumn()>column) {
                        playerInLine = p.getPlayerNumber();
                    }
                    break;
                case FACING_WEST:
                    if(p.getPosition().getColumn()==column && p.getPosition().getRow()<row) {
                        playerInLine = p.getPlayerNumber();
                    }
                    break;
                case FACING_SOUTH:
                    if(p.getPosition().getRow()==row && p.getPosition().getColumn()<column) {
                        playerInLine = p.getPlayerNumber();
                    }
                    break;
                case FACING_EAST:
                    if(p.getPosition().getColumn()==column && p.getPosition().getRow()>row) {
                        playerInLine = p.getPlayerNumber();
                    }
                    break;
            }
        }
        return playerInLine;
    }
}
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
    private ArrayList<IRobot> robots;
    private int flagsInitiated; // How many flags have been initiated so far.(So that you only win when you reach the last one)
    private int robotsInitiated; // How many robots have been initiated so far.
    private SoundContainer sound;

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
        this.robots = builder.getRobots();
        this.rows = builder.getRows();
        this.columns = builder.getColumns();
        this.robotsInitiated = builder.getRobotsInitiated();
        this.flagsInitiated = builder.getFlagsInitiated();
        this.sound = new SoundContainer();
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
     * Get the number of robots in TileGrid
     * @return Robots in grid
     */
    int getRobotsInitiated(){
        return robotsInitiated;
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
     * Runs trough the grid to find the robots.
     * Then activates a function to find out what kind of tile the robot is standing on.
     */
    public void activateTiles(){
        activateTiles(0);
    }

    public void activateTiles(int currentPhase){
        for (IRobot robot : this.robots) {
            fireControl(robot.getRobotNumber());
        }
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                for (IRobot robot : robots) {
                    if (tile.hasRobot(robot) && !robot.hasMoved()) {
                        robotOnTile(tile, robot, currentPhase);
                        robot.moved(true);
                    }
                }
            }
        }
        for(IRobot robot : robots){
            robot.moved(false);
            removeRobotLaser(robot.getRobotNumber());
        }
    }

    /**
     * Finds out what kind of tile the robot is standing on and
     * if it has a function which effects the robot.
     * @param tile The active file
     * @param robot The active robot
     */
    private void robotOnTile(Tile tile, IRobot robot, int currentPhase) {
        // Laser
        if(tile.hasGameObject(GameObjectType.LASER_BEAM)){
            sound.laserSound();
            laserDamageRobot(((LaserBeam)tile.getGameObject(GameObjectType.LASER_BEAM)).isDual(),robot);
        }
        // Conveyor
        else if(tile.hasGameObject(GameObjectType.CONVEYOR)) {
            Conveyor conveyor = (Conveyor) tile.getGameObject(GameObjectType.CONVEYOR);
            sound.conveyorSound();
            moveInDirectionOfConveyor(conveyor, robot.getRobotNumber());
        }
        // Repair Station
        else if(tile.hasGameObject(GameObjectType.REPAIR_STATION)){
            if (robot.isFinished()) {
                robot.repair();
                robot.setBackUp();
            }
        }
        // Flag
        else if(tile.hasGameObject(GameObjectType.FLAG)){
            if(robot.isFinished()){
                int n = ((Flag)tile.getGameObject(GameObjectType.FLAG)).getFlagNumber();
                //Adds flag to flagsVisited only if it has visited all previous flags.
                if(n==1||robot.getFlag(n-1)){
                    //Creates a backUp
                    robot.setBackUp();
                    if(!robot.getFlag(n)) {
                        robot.visitFlag(n);
                    }
                    System.out.println("Visited: " + robot.getFlagsVisited().toString());

                    //if you are on the last flag, and visited all previous, you win.
                    if (n >= flagsInitiated) {
                        robot.win();
                    }
                }
            }
        }
        //Teleporter
        else if(tile.hasGameObject(GameObjectType.TELEPORTER)&& (robot.getMoveProgression() == 0)){
            sound.teleportSound();
            Teleporter teleporter = (Teleporter)tile.getGameObject(GameObjectType.TELEPORTER);
            tile.removeObjectFromTile(robot);
            getTile(teleporter.getTeleportLocation()).addObjectOnTile(robot);
            setRobotPosition(robot.getRobotNumber(),teleporter.getTeleportLocation());
        }
        // Hole
        else if(tile.hasGameObject(GameObjectType.HOLE)){
            respawnRobot(robot.getRobotNumber());
        }

        else if(tile.hasGameObject(GameObjectType.LASER_OUTLET)){
            laserDamageRobot(((LaserOutlet)tile.getGameObject(GameObjectType.LASER_OUTLET)).isDual(),robot);
        }
        // Rotator activation
        else if(tile.hasGameObject(GameObjectType.ROTATOR_CLOCKWISE)){
            applyRotation(Program.RIGHT, robot.getRobotNumber());
        }
        else if(tile.hasGameObject(GameObjectType.ROTATOR_COUNTER_CLOCKWISE)){
            applyRotation(Program.LEFT, robot.getRobotNumber());
        }
        // Pusher activation
        else if(tile.hasGameObject(GameObjectType.PUSHER)){
            sound.pusherSound();
            Pusher pusher = (Pusher) tile.getGameObject(GameObjectType.PUSHER);
            Orientation orientation = pusher.getOrientation();

            int n[] = calculateMove(orientation);
            if((currentPhase+1)%2==0 && pusher.isEven()){
                moveRobot(robot.getRobotNumber(),n[0],n[1]);
            }
            else if((currentPhase+1)%2 != 0 && !pusher.isEven()){
                moveRobot(robot.getRobotNumber(),n[0],n[1]);
            }
        }
        else{
            sound.move();
        }
    }
    /**
     * Damage a robot by laser
     * @param dual Is the laser Dual
     * @param robot the robot to be damaged
     */
    private void laserDamageRobot(boolean dual, IRobot robot){
        if (dual) {
            if (robot.receiveDamage(2)) respawnRobot(robot);
        }
        else {
            if (robot.receiveDamage()) respawnRobot(robot);
        }
    }
    /**
     * Moves robot on conveyor
     * @param conveyor Conveyor the robot is on
     * @param robotNumber Robots number
     */
    private void moveInDirectionOfConveyor(Conveyor conveyor, int robotNumber){
        IRobot robot = getRobot(robotNumber);
        if(conveyor.getTurn() > 0){
            applyRotation(Program.RIGHT,robotNumber);
        }
        if(conveyor.getTurn() < 0){
            applyRotation(Program.LEFT,robotNumber);
        }
        int[] move = calculateMove(conveyor.getOrientation());
        int rowsToMove = move[0];
        int colsToMove = move[1];

        Coordinate nextTile = robot.getPosition().moveCoordinate(rowsToMove,colsToMove);

        moveRobot(robotNumber,rowsToMove,colsToMove);
        if(conveyor.isFast() && !robot.hasMoved()){
            robot.moved(true);
            try{
                if(getTile(nextTile).hasGameObject(GameObjectType.CONVEYOR)){
                    Conveyor nextConveyor = (Conveyor) getTile(nextTile).getGameObject(GameObjectType.CONVEYOR);
                    moveInDirectionOfConveyor(nextConveyor,robotNumber);
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
     * Figures out the movement queue for the next phase based on the Program Card priorities.
     * @return A list
     */
    public ArrayList<Integer> robotQueue(){
        ArrayList<Integer> robotQueue = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>();
        for(IRobot robot : this.robots){
            priorities.add(robot.getNextProgramPriority());
        }
        while(!priorities.isEmpty()){
            Integer priority=0;
            for(Integer p : priorities){
                if(priority<=p){
                    priority=p;
                }
            }
            robotQueue.add(priorities.indexOf(priority));
            priorities.remove(priority);
        }
        return robotQueue;
    }

    /**
     * Apply the next program in the robots queue.
     * @param robotNumber Robot number
     */
    void applyNextProgram(int robotNumber){
        getRobot(robotNumber).setNextProgram();
    }

    /**
     *Apply rotation to a robot
     * @param move the rotation to be applied.
     * @param robotNumber the identifier of the robot whose move should be continued.
     */
    private void applyRotation(Program move, int robotNumber){
        getRobot(robotNumber).updateOrientation(move);
    }

    /**
     * Method that makes a robot perform a move.
     * @param move the move to apply
     * @param robotNumber the number of the robot that the move should be applied to
     */
    private void applyMove(Program move, int robotNumber){
        int[] movement = calculateMove(getRobot(robotNumber).getOrientation());
        int rowsToMove = movement[0];
        int columnsToMove = movement[1];

        if(move==Program.BACK){
            rowsToMove *= -1;
            columnsToMove *= -1;
        }
        moveRobot(robotNumber, rowsToMove, columnsToMove);
    }

    /**
     * Method that continues the move a robot has in progress.
     * @param robotNumber the identifier of the robot whose move should be continued.
     */
    public void continueMove(int robotNumber){
        Program currentMove = getRobot(robotNumber).getCurrentMove();
        int moveProgression = getRobot(robotNumber).getMoveProgression();

        if(moveProgression==currentMove.totalMoves()){
            getRobot(robotNumber).setCurrentMove(Program.NONE);
            getRobot(robotNumber).resetMoveProgress();
        }
        else{
            if(!currentMove.isMove()){
                applyRotation(currentMove, robotNumber);
            }else{
                applyMove(currentMove, robotNumber);
            }
            getRobot(robotNumber).progressMove();
        }
    }

    /**
     * Move the robot
     * @param robotNumber Robot number
     * @param rowsToMove Rows the robot is to move
     * @param columnsToMove Columns the robot is to move
     */
    public void moveRobot(int robotNumber, int rowsToMove, int columnsToMove){
        int rowOfRobot = getRobotPosition(robotNumber).getRow();
        int columnOfRobot = getRobotPosition(robotNumber).getColumn();

        if(!canMoveRobot(robotNumber, rowsToMove, columnsToMove)) return;

        this.tileGrid[rowOfRobot][columnOfRobot].removeObjectFromTile(getRobot(robotNumber));
        this.tileGrid[rowOfRobot+rowsToMove][columnOfRobot+columnsToMove].addObjectOnTile(getRobot(robotNumber));
        setRobotPosition(robotNumber, getRobot(robotNumber).getPosition().moveCoordinate(rowsToMove,columnsToMove));
    }

    /**
     * TODO Simplify the if statements
     * Checks if the robot can move.
     * @param robotNumber Robots number
     * @param rowsToMove How many rows the robot needs to move
     * @param columnsToMove How many columns robot needs to move
     * @return Boolean for if the robot can move.
     */
    private boolean canMoveRobot(int robotNumber, int rowsToMove, int columnsToMove){
        Coordinate coordinateOfRobot = getRobotPosition(robotNumber);
        Orientation directionOfMove = findOrientationOfMovement(rowsToMove, columnsToMove);
        if(robotBlockedOnCurrentTile(getRobot(robotNumber), directionOfMove)){
            return false;
        }
        if(robotOutOfBounds(coordinateOfRobot,rowsToMove,columnsToMove)){
            respawnRobot(robotNumber);
            return false;
        }
        if(robotBlockedOnNextTile(getRobot(robotNumber), directionOfMove,rowsToMove,columnsToMove)){
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
     * @param robot Robot in question
     * @return If the path is blocked on the Tile
     */
    private boolean robotBlockedOnCurrentTile(IRobot robot, Orientation directionOfMove){
        Tile tile = getTile(robot.getPosition());
        return tile.robotPathBlocked(robot, directionOfMove);
    }
    /**
     * @param robot Active robot
     * @param rowsToMove Rows to where the robot is moving
     * @param columnsToMove Columns to where the robot is moving
     * @return If robot can move, or is blocked by wall
     */
    private boolean robotBlockedOnNextTile(IRobot robot, Orientation directionOfMove, int rowsToMove, int columnsToMove){
        Coordinate coordinate = robot.getPosition().moveCoordinate(rowsToMove,columnsToMove);
        if(getTile(coordinate).hasGameObject(GameObjectType.ROBOT)){

            IRobot otherRobot = (IRobot)getTile(coordinate).getGameObject(GameObjectType.ROBOT);
            int[] move= calculateMove(directionOfMove);
            moveRobot(otherRobot.getRobotNumber(),move[0],move[1]);

            //If the robot who pushes has a ramming gear ability, the other robot takes some damage
            if(robotHasAbility(robot.getRobotNumber(),Ability.RammingGear)){
                otherRobot.receiveDamage();
            }
        }


        return getTile(coordinate).robotPathBlocked(robot, directionOfMove);
    }

    /**
     * Finds out if the robot is moving out of bounds
     * @param coordinateOfRobot Current position of robot in question
     * @param rowsToMove The number of rows to be moved
     * @param columnsToMove The number of columns to be moved
     * @return Is the move out of bounds.
     */
    private boolean robotOutOfBounds(Coordinate coordinateOfRobot, int rowsToMove, int columnsToMove){
        Coordinate newCoordinate = coordinateOfRobot.moveCoordinate(rowsToMove,columnsToMove);

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
     * Get robot
     * @param robotNumber Robots number
     * @return Wanted robot
     */
    public IRobot getRobot(int robotNumber){
        return this.robots.get(robotNumber);
    }

    /**
     * Get all robots
     * @return List of robots
     */
    ArrayList<IRobot> getRobots() {
        return robots;
    }

    /**
     * Finds the player on the bord.
     * @return Returns the player
     */
    IRobot getPlayer(){
        for(IRobot robot : this.robots){
            if(!robot.isAI()){
                return robot;
            }
        }
        return null;
    }

    /**
     * Reset a robot
     * @param robotNumber Robots number
     */
    void resetRobot(int robotNumber){
        getRobot(robotNumber).reset();
    }

    void resetRobots(){
        for(IRobot robot : this.robots){
            robot.reset();
        }
    }

    /**
     * Respawns the robot after it has fallen out of grid, with health=6.
     * @param robotNumber Robots number
     */
    private void respawnRobot(int robotNumber){
        respawnRobot(getRobot(robotNumber));
    }
    /**
     * Respawn the robot after it has fallen out of grid, with health=6.
     * @param robot The Robot
     */
    private void respawnRobot(IRobot robot){
        getTile(robot.getPosition()).removeObjectFromTile(robot);
        robot.respawn();
        getTile(robot.getBackUp()).addObjectOnTile(robot);
    }

    /**
     * Get Robotss Coordinates
     * @param robotNumber Robots number
     * @return Robots current position
     */
    public Coordinate getRobotPosition(int robotNumber){
        return getRobot(robotNumber).getPosition();
    }

    /**
     * Get Robots ProgramHand
     * @param robotNumber Robots number
     * @return Robots ProgramHand
     */
    ArrayList<ProgramCard> getRobotProgramHand(int robotNumber){
        return getRobot(robotNumber).getProgramHand();
    }

    /**
     * Get robots current move
     * @param robotNumber Robots Number
     * @return Program of current move
     */
    Program getRobotCurrentMove(int robotNumber){
        return getRobot(robotNumber).getCurrentMove();
    }

    /**
     * Check if the robot har finished the current move.
     * @param robotNumber The robot number
     * @return If the Current Program is set to none.
     */
    Boolean robotFinishedCurrentMove(int robotNumber){
        return getRobotCurrentMove(robotNumber).equals(Program.NONE);
    }

    /**
     * Get the robots ability
     * @param robotNumber The robots number
     * @return The robots ability
     */
    boolean robotHasAbility(int robotNumber, Ability ability){
        return getRobot(robotNumber).hasAbility(ability);
    }

    /**
     * Set new position of robot
     * @param robotNumber robots number
     * @param coordinate New Coordinate
     */
    private void setRobotPosition(int robotNumber, Coordinate coordinate){
        getRobot(robotNumber).setPosition(coordinate);
    }

    public void fireControl(int robotNumber) {
        Coordinate position = getRobotPosition(robotNumber);
        if(robotInLine(robotNumber) < 0) {
            return;
        }
        if(robotHasAbility(robotNumber, Ability.PressorBeam)) {
            int robotToMove = robotInLine(robotNumber);
            int[] movement=calculateMove(position.getOrientation());
            if(robotToMove!=robotNumber) {
                if(continueBeam(getRobot(robotNumber).getPosition(), getRobot(robotToMove).getPosition())) {
                    moveRobot(robotToMove, movement[0], movement[1]);
                }
            }
        } else if(robotHasAbility(robotNumber, Ability.TractorBeam)) {
            int robotToMove = robotInLine(robotNumber);
            int[] movement=calculateMove(position.getOrientation().opposite());
            if(robotToMove!=robotNumber) {
                if(continueBeam(getRobot(robotNumber).getPosition(), getRobot(robotToMove).getPosition())) {
                    moveRobot(robotToMove, movement[0], movement[1]);
                }
            }
        } else {
            fireRobotLaser(robotNumber);
        }
    }

    public boolean continueBeam(Coordinate position, Coordinate opponentPosition) {

        while (!position.equals(opponentPosition)) {
            if(!tileCheck(position, false)){
                return false;
            }
            position = position.moveCoordinate();
        }
        return true;
    }

    /**
     * Adds laser to board if robot wants to fire.
     * @param robotNumber robot who is to fire laser
     */
    public void fireRobotLaser(int robotNumber) {
        Coordinate position = getRobotPosition(robotNumber);
        position.setOrientation(getRobot(robotNumber).getOrientation());
        Orientation laserOrientation = position.getOrientation().laserOrientation();
        boolean dual = robotHasAbility(robotNumber, Ability.DoubleBarreledLaser);

        boolean highPowered = robotHasAbility(robotNumber, Ability.HighPoweredLaser);

        boolean firing = continueFiring(position);
        if(!firing && highPowered){
            firing = true;
            highPowered = false;
        }

        while (firing) {
            position = position.moveCoordinate();
            getTile(position).addObjectOnTile(new LaserBeam(laserOrientation,dual, robotNumber));
            firing = continueFiring(position);

            if(!firing && highPowered){
                firing = true;
                highPowered = false;
            }
        }
        if(robotHasAbility(robotNumber, Ability.RearFiringLaser)){
            position = getRobotPosition(robotNumber);
            position.setOrientation(getRobot(robotNumber).getOrientation().opposite());

            firing = continueFiring(position);
            if(!firing && highPowered){
                firing = true;
                highPowered = false;
            }

            while (firing) {
                position = position.moveCoordinate();
                getTile(position).addObjectOnTile(new LaserBeam(laserOrientation,dual, robotNumber));
                firing = continueFiring(position);

                if(!firing && highPowered){
                    firing = true;
                    highPowered = false;
                }
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
        if(getTile(position).hasGameObject(GameObjectType.ROBOT)){
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
        else if(tile.hasGameObject(GameObjectType.ROBOT)){
            return true;
        }
        else if(tile.orientationBlocked(position.getOrientation())){
            return true;
        }
        return false;
    }

    /**
     * Removes the robots fired laser
     * @param robotNumber robotNumber
     */
    public void removeRobotLaser(int robotNumber){
        Coordinate position = getRobotPosition(robotNumber);
        position.setOrientation(getRobot(robotNumber).getOrientation());

        boolean firing = continueFiring(position);
        while (firing) {
            position = position.moveCoordinate();
            getTile(position).removeRobotLaserFromTile(robotNumber);
            firing = continueFiring(position);
        }

        if(robotHasAbility(robotNumber, Ability.RearFiringLaser)){
            position = getRobotPosition(robotNumber);
            position.setOrientation(getRobot(robotNumber).getOrientation().opposite());

            firing = continueFiring(position);
            while (firing) {
                position = position.moveCoordinate();
                getTile(position).removeRobotLaserFromTile(robotNumber);
                firing = continueFiring(position);
            }
        }
    }

    /**
     * Checks if there is a robot in line of sight.
     * @param robotNumber
     * @return robotNumber of robot in line of sight.
     */
    public int robotInLine(int robotNumber) {

        int column = getRobot(robotNumber).getPosition().getColumn();
        int row = getRobot(robotNumber).getPosition().getRow();
        int robotInLine = robotNumber;

        for(IRobot p : robots) {
            switch (getRobot(robotNumber).getOrientation()) {
                case FACING_NORTH:
                    if(p.getPosition().getRow()==row && p.getPosition().getColumn()>column) {
                        robotInLine = p.getRobotNumber();
                    }
                    break;
                case FACING_WEST:
                    if(p.getPosition().getColumn()==column && p.getPosition().getRow()<row) {
                        robotInLine = p.getRobotNumber();
                    }
                    break;
                case FACING_SOUTH:
                    if(p.getPosition().getRow()==row && p.getPosition().getColumn()<column) {
                        robotInLine = p.getRobotNumber();
                    }
                    break;
                case FACING_EAST:
                    if(p.getPosition().getColumn()==column && p.getPosition().getRow()>row) {
                        robotInLine = p.getRobotNumber();
                    }
                    break;
                default:
                    robotInLine = -1;
            }
        }
        return robotInLine;
    }

    public void decideAiPrograms() {
        for (IRobot robot : robots) {
            if (robot.isAI()) {
                ((AI) robot).decideProgram(getPlayer().getPosition());
            }
        }
    }
    /**
     * Removes player from the game when out of lives.
     */
    public void removePlayer() {
        ArrayList<IRobot> newRobots = new ArrayList<>(robots);
        for(IRobot robot : newRobots) {
            if(robot.getLives() < 1) {
                getTile(robot.getPosition()).removeObjectFromTile(robot);
                robots.remove(robot);
            }
        }
    }
}
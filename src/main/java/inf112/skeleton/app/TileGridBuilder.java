package inf112.skeleton.app;

import inf112.skeleton.app.gameobjects.*;
import inf112.skeleton.app.gameobjects.Robots.IRobot;
import inf112.skeleton.app.gameobjects.Robots.Player;
import inf112.skeleton.app.gameobjects.tiletypes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TileGridBuilder {
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private String fileName = "./assets/maps/";
    private IRobot[] players;
    private int flagsInitiated; // How many flags have been initiated so far.(So that you only win when you reach the last one)
    private int playersInitiated; // How many players have been initiated so far.

    public TileGridBuilder(String file){
        if(file.contains("Test")){
            this.fileName+= "testMaps/";
        }
        fileName +=file;
        this.playersInitiated = 0;
        this.flagsInitiated = 0;
        initiateTiles();
        activateTileFunctions();
    }

    /**
     * Initiates every tile in the grid
     * Todo: Implement reading tile-layout from file, or randomisation.
     */
    private void initiateTiles(){
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(this.fileName);
            // Designate the space used in the file between tiles and objects
            String space =" ";
            String split = ",";

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            getMapInfo(bufferedReader.readLine());

            for(int row = this.rows-1; row>=0; row--){
                String nextTileTypeLine = bufferedReader.readLine();
                String[] nextTileTypeLineArray = nextTileTypeLine.split(space);
                for(int column = this.columns-1; column>=0; column--) {
                    String nextTileTypesOfColumn = nextTileTypeLineArray[column];
                    this. tileGrid[row][column] = new Tile(GameObjectType.STANDARD_TILE);
                    String[] typesOnTile = nextTileTypesOfColumn.split(split);

                    for (String s : typesOnTile) {
                        // Adding objects on top of tile
                        if (!s.equals(space)) {  // If tile type is not standardTile
                            stringToGameObjectType(s, row, column);
                        }
                    }
                }
            }
            System.out.println("Players: " + getPlayers().toString());
            for(IRobot player : getPlayers()){
                player.setFlagsVisitedSize(getFlagsInitiated());
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
    private void stringToGameObjectType(String nextTileType, int row, int column){
        Orientation orientation;
        switch(nextTileType.substring(0,1)){
            case "C":
                //One speed conveyors
                boolean fast = nextTileType.contains("CC");

                int rotating;
                if (nextTileType.contains("R"))
                    rotating = 1;
                else if(nextTileType.contains("L"))
                    rotating = -1;
                else
                    rotating = 0;
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new Conveyor(orientation,fast,rotating));
                break;
            case "D":
                boolean evenPusher = nextTileType.contains("DD");
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new Pusher(orientation,evenPusher));
                break;
            case "F":
                int n = Character.getNumericValue(nextTileType.charAt(nextTileType.length()-1));

                if(n < 1 || n > 9){
                    n = flagsInitiated+1;
                }

                this.tileGrid[row][column].addObjectOnTile(new Flag(n));
                flagsInitiated ++;
                break;
            case "G":
                int teleporterNr = Character.getNumericValue(nextTileType.charAt(nextTileType.length()-1));
                this.tileGrid[row][column].addObjectOnTile(new Teleporter(teleporterNr, row, column));
                break;
            case "H":
                this.tileGrid[row][column].addObjectOnTile(new Hole());
                break;
            case "L":
                boolean dualLaser = nextTileType.contains("LL");
                if( nextTileType.contains("H"))
                    orientation = Orientation.HORIZONTAL;
                else if(nextTileType.contains("V"))
                    orientation = Orientation.VERTICAL;
                else
                    orientation = Orientation.VERTICAL;
                this.tileGrid[row][column].addObjectOnTile(new LaserBeam(orientation, dualLaser));
                break;
            case "O":
                boolean dualOutlet = nextTileType.contains("OO");
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new LaserOutlet(orientation, dualOutlet, row, column));
                break;
            case "P":
                orientation = stringToOrientation(nextTileType);
                if(nextTileType.contains("Robots")){

                }
                else {
                    Player newPlayer;
                    newPlayer = new Player(this.playersInitiated, orientation);
                    this.tileGrid[row][column].addObjectOnTile(newPlayer);
                    this.players[this.playersInitiated++] = newPlayer; // Add new player to list of players.
                    newPlayer.initiate(new Coordinate(row, column));
                }
                break;
            case "R":
                this.tileGrid[row][column].addObjectOnTile(new RepairStation());
                break;
            case "T":
                boolean counterClockwise = nextTileType.contains("CC");
                this.tileGrid[row][column].addObjectOnTile(new Rotator(counterClockwise));
                break;
            case "W":
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new Wall(orientation));
                break;
        }
    }
    /**
     * Findsorentation of tile element
     * @param nextTileType String of what the next element is
     * @return The orientation of the element
     */
    private Orientation stringToOrientation(String nextTileType){
        switch (nextTileType.charAt(nextTileType.length() - 1)){
            case '1':
                return Orientation.FACING_NORTH;
            case '2':
                return Orientation.FACING_EAST;
            case '3':
                return Orientation.FACING_SOUTH;
            case '4':
                return Orientation.FACING_WEST;
            default:
                return Orientation.FACING_NORTH;
        }
    }
    /**
     * Takes a String from the buffered reader and gets the map information
     * @param mapInfo String containing map info
     */
    private void getMapInfo(String mapInfo){
        Scanner s = new Scanner(mapInfo);
        this.rows =s.nextInt();
        this.columns = s.nextInt();
        this.players = new Player[s.nextInt()];
        this.tileGrid = new Tile[this.rows][this.columns];
        s.close();
    }

    /**
     * Finds uncoupled teleporters
     */
    private void activateTileFunctions(){
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                if (tile.hasGameObject(GameObjectType.TELEPORTER)) {
                    Teleporter teleporter = (Teleporter)tile.getGameObject(GameObjectType.TELEPORTER);
                    if(!teleporter.isCoupled()){
                        findTeleporterPartner (teleporter);
                    }
                }
                if(tile.hasGameObject(GameObjectType.LASER_OUTLET)){
                    addLaserFromOutlet((LaserOutlet) tile.getGameObject(GameObjectType.LASER_OUTLET));
                }
            }
        }
    }

    /**
     * Couples teleporters together
     * @param teleporter teleporter to be coupled
     */
    private void findTeleporterPartner(Teleporter teleporter){
        for(Tile[] tileRow : tileGrid){
            for(Tile tile : tileRow){
                if (tile.hasGameObject(GameObjectType.TELEPORTER)&& !tile.hasGameObject(teleporter)) {
                    Teleporter partner = (Teleporter) tile.getGameObject(GameObjectType.TELEPORTER);
                    if(teleporter.getTeleporterNr()==partner.getTeleporterNr()){
                        teleporter.setTeleportLocation(partner.getPosition());
                        partner.setTeleportLocation(teleporter.getPosition());
                    }
                }
            }
        }
    }

    /**
     * Adds laser from the outlets to the map.
     */
    private void addLaserFromOutlet(LaserOutlet outlet){
        boolean dual = outlet.isDual();
        Coordinate position = outlet.getPosition();
        Orientation laserOrientation = position.getOrientation().laserOrientation();

        boolean firing = continueFiring(position);
        while (firing) {
            position = position.moveCoordinate();
            getTile(position).addObjectOnTile(new LaserBeam(laserOrientation,dual));
            firing = continueFiring(position);
        }
    }

    /**
     * Figures out if the laser can keep firing
     * @param pos lasers current position
     * @return If hte laser can keep firing.
     */
    private boolean continueFiring(Coordinate pos){
        Coordinate position = new Coordinate(pos.getRow(),pos.getColumn(), pos.getOrientation());
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
     * @return Get the built grid
     */
    public Tile[][] getTileGrid() {
        return this.tileGrid;
    }

    /**
     * @return get the number of rows in the grid
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * @return get the number of columns in the grid
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * @return get the number of
     */
    public IRobot[] getPlayers() {
        return this.players;
    }

    /**
     * @return get the number of flags in the grid
     */
    public int getFlagsInitiated() {
        return this.flagsInitiated;
    }

    /**
     * @return get the number of players in the grid.
     */
    public int getPlayersInitiated() {
        return this.playersInitiated;
    }

    /**
     * Get Tile based on coordinate
     * @param coordinate Coordinate of the requested Tile
     * @return Tile at specified coordinate
     */
    public Tile getTile(Coordinate coordinate){
        return tileGrid[coordinate.getRow()][coordinate.getColumn()];
    }
}

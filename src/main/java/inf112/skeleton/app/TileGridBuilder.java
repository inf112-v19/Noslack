package inf112.skeleton.app;

import inf112.skeleton.app.gameobjects.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TileGridBuilder {
    private Tile[][] tileGrid;
    private int rows;
    private int columns;
    private String fileName = "./assets/maps/";
    private Player[] players;
    private int flagsInitiated; // How many flags have been initiated so far.(So that you only win when you reach the last one)
    private int playersInitiated; // How many players have been initiated so far.

    public TileGridBuilder(String file){
        fileName +=file;
        this.playersInitiated = 0;
        this.flagsInitiated = 0;
        initiateTiles();
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
            System.out.println("Players: " + getPlayers());
            for(Player player : getPlayers()){
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
            case "W":
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new Wall(orientation));
                break;
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
            case "F":
                /*
                 * If there was no number after F, it cast "F" to ascii int 70.
                 * If its not a number between 1-9, we just set n as the lowest unused number between 1-9
                 */

                char ch = nextTileType.charAt(nextTileType.length()-1);//At the moment it only takes 1 digit.
                int n = Character.getNumericValue(ch);

                if(n < 1 || n > 9){
                    n = flagsInitiated+1;
                }

                this.tileGrid[row][column].addObjectOnTile(new Flag(n));
                flagsInitiated ++;
                break;
            case "H":
                this.tileGrid[row][column].addObjectOnTile(new Hole());
                break;
            case "R":
                this.tileGrid[row][column].addObjectOnTile(new RepairStation());
                break;
            case "D":
                boolean evenPusher = nextTileType.contains("DD");
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new Pusher(orientation,evenPusher));
                break;
            case "O":
                boolean dualOutlet = nextTileType.contains("OO");
                orientation = stringToOrientation(nextTileType);
                this.tileGrid[row][column].addObjectOnTile(new LaserOutlet(orientation, dualOutlet));
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
            case "T":
                boolean counterClockwise = nextTileType.contains("CC");
                this.tileGrid[row][column].addObjectOnTile(new Rotator(counterClockwise));
                break;
            case "P":
                Player newPlayer;
                orientation = stringToOrientation(nextTileType);
                newPlayer = new Player(this.playersInitiated, orientation);
                this.tileGrid[row][column].addObjectOnTile(newPlayer);
                this.players[this.playersInitiated++] = newPlayer; // Add new player to list of players.
                newPlayer.initiate(new Coordinate(row, column));
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
    public Player[] getPlayers() {
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
}

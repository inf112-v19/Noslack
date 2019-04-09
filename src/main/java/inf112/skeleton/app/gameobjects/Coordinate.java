package inf112.skeleton.app.gameobjects;

import static java.lang.Math.abs;

public class Coordinate {

    private int row;
    private int column;
    private Orientation orientation;

    public Coordinate(int row, int column){
        this.row = row;
        this.column = column;
        this.orientation = Orientation.FACING_NORTH;
    }

    public Coordinate(int row, int column, Orientation orientation){
        this.row = row;
        this.column = column;
        this.orientation = orientation;
    }

    public Coordinate (Coordinate coordinate){
        this.row = coordinate.getRow();
        this.column = coordinate.getColumn();
        this.orientation = coordinate.getOrientation();
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void moveCoordinate(int rowsToMove, int columnsToMove){
        this.row += rowsToMove;
        this.column += columnsToMove;
    }
    public Coordinate moveCoordinate(){
        int row = this.row;
        int column = this.column;
        switch (this.orientation){
            case FACING_NORTH:
                row++;
                break;
            case FACING_SOUTH:
                row--;
                break;
            case FACING_EAST:
                column++;
                break;
            case FACING_WEST:
                column--;
                break;
        }
        return new Coordinate(row, column, this.orientation);
    }


    @Override
    public boolean equals(Object o) {
        if(o.getClass()==this.getClass())
            return this.row == ((Coordinate) o).getRow() && this.column == ((Coordinate) o).getColumn();
        return false;
    }
    public String toString(){
        return "Row: "+getRow()+" Column: "+getColumn();
    }

    /**
     * Find the closest orientation to turn to between two coordinates
     * @param coordinate Coordinate nr 2
     * @return The orientation
     */
    public Orientation orientationToPosition(Coordinate coordinate){
        int[] difference = this.difference(coordinate);
        if(abs(difference[1]) < abs(difference[0])){
            if(difference[0]<0) {
                return Orientation.FACING_SOUTH;
            }
            else {
                return Orientation.FACING_NORTH;
            }
        }
        else if (abs(difference[0]) < abs(difference[1])){
            if(difference[1]<0){
                return Orientation.FACING_WEST;
            }
            else{
                return Orientation.FACING_EAST;
            }
        }
        else{
            if(difference[0]<0){
                return Orientation.FACING_SOUTH;
            }
            else{
                return Orientation.FACING_NORTH;
            }
        }
    }

    /**
     * The difference between two coordinates
     * @param coordinate Coordinate number two
     * @return An int[] where int[0] is the row difference, and int[1] is the column difference
     */
    private int[] difference(Coordinate coordinate){
        int[] difference = new int[2];
        difference[0] = coordinate.getRow()-this.row;
        difference[1] = coordinate.getColumn()-this.column;
        return difference;
    }
}

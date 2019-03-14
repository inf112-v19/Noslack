package inf112.skeleton.app.gameobjects;

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

    public int getRow(){
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Orientation getOrientation(){
        return orientation;
    }
}

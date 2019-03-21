package inf112.skeleton.app.gameobjects;

import java.util.Objects;

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
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass()==this.getClass())
            return this.row == ((Coordinate) o).getRow() && this.column == ((Coordinate) o).getColumn();
        return false;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}

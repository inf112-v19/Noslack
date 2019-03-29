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


    @Override
    public boolean equals(Object o) {
        if(o.getClass()==this.getClass())
            return this.row == ((Coordinate) o).getRow() && this.column == ((Coordinate) o).getColumn();
        return false;
    }
    public String toString(){
        return "Row: "+getRow()+" Column: "+getColumn();
    }
}

package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;

import static java.lang.Math.abs;

public class AICoordinate{
    private int row;
    private int column;
    private Orientation orientation;

    public AICoordinate (Coordinate coordinate){
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

    public void moveAICoordiante(Program program){
        switch(program){
            case LEFT:
            case RIGHT:
            case U:
                this.orientation = this.orientation.rotate(program);
                break;
            case BACK:
                this.moveInDirection(-1);
                break;
            case BACK2:
                this.moveInDirection(-2);
                break;
            default:
                this.moveInDirection(program.totalMoves());
                break;
        }
    }

    private void moveInDirection(int move){
        switch (this.orientation){
            case FACING_NORTH:
                this.row+=move;
                break;
            case FACING_SOUTH:
                this.row-=move;
                break;
            case FACING_EAST:
                this.column+=move;
                break;
            case FACING_WEST:
                this.column-=move;
                break;
        }
    }


    @Override
    public boolean equals(Object o) {
        if(o.getClass()==this.getClass())
            return this.row == ((Coordinate) o).getRow() && this.column == ((Coordinate) o).getColumn();
        return false;
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

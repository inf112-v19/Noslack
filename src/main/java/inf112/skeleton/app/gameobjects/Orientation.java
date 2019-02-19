package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;

public enum Orientation {
    FACING_NORTH,
    FACING_WEST,
    FACING_SOUTH,
    FACING_EAST;

    public static Orientation rotate(Orientation orientation, Program rotation){

        switch(orientation){
            case FACING_NORTH:
                switch(rotation){
                    case LEFT: return FACING_WEST;
                    case RIGHT: return FACING_EAST;
                    case U: return FACING_SOUTH;
                }
            case FACING_WEST:
                switch(rotation){
                    case LEFT: return FACING_SOUTH;
                    case RIGHT: return FACING_NORTH;
                    case U: return FACING_EAST;
                }
            case FACING_SOUTH:
                switch (rotation){
                    case LEFT: return FACING_EAST;
                    case RIGHT: return FACING_WEST;
                    case U: return FACING_NORTH;
                }
            case FACING_EAST:
                switch (rotation){
                    case LEFT: return FACING_NORTH;
                    case RIGHT: return FACING_SOUTH;
                    case U: return FACING_WEST;
                }
            default:
                return orientation;
        }

    }

}

package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;

public enum Orientation {
    FACING_NORTH,
    FACING_WEST,
    FACING_SOUTH,
    FACING_EAST,
    NONE;

    /**
     * Rotates the orientation from the rartionen given from ProgramCard.
     * @param rotation Program with orientation wanted.
     * @return New orientation after rotation
     */
    public Orientation rotate(Program rotation){
        if(rotation.equals(Program.MOVE3)||rotation.equals(Program.MOVE2)||rotation.equals(Program.MOVE1)
                ||rotation.equals(Program.BACK))
            return this;
        switch (this) {
            case FACING_NORTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_EAST;
                    case LEFT:
                        return FACING_WEST;
                    case U:
                        return FACING_SOUTH;
                }
            case FACING_SOUTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_WEST;
                    case LEFT:
                        return FACING_EAST;
                    case U:
                        return FACING_NORTH;
                }
            case FACING_EAST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_SOUTH;
                    case LEFT:
                        return FACING_NORTH;
                    case U:
                        return FACING_WEST;
                }
            case FACING_WEST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_NORTH;
                    case LEFT:
                        return FACING_SOUTH;
                    case U:
                        return FACING_EAST;
                }
            default: return this;
        }

    }

}

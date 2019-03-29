package inf112.skeleton.app.gameobjects;

import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;

public enum Orientation {
    FACING_NORTH,
    FACING_WEST,
    FACING_SOUTH,
    FACING_EAST,
    HORIZONTAL,
    VERTICAL,
    NONE;

    /**
     * Rotates the orientation from the rartionen given from ProgramCard.
     * @param rotation Program with orientation wanted.
     * @return New orientation after rotation
     */
    public Orientation rotate(Program rotation){
        switch (this) {
            case FACING_NORTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_EAST;
                    case LEFT:
                        return FACING_WEST;
                    case U:
                        return FACING_SOUTH;
                    default: return this;
                }
            case FACING_SOUTH:
                switch (rotation) {
                    case RIGHT:
                        return FACING_WEST;
                    case LEFT:
                        return FACING_EAST;
                    case U:
                        return FACING_NORTH;
                    default: return this;
                }
            case FACING_EAST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_SOUTH;
                    case LEFT:
                        return FACING_NORTH;
                    case U:
                        return FACING_WEST;
                    default: return this;
                }
            case FACING_WEST:
                switch (rotation) {
                    case RIGHT:
                        return FACING_NORTH;
                    case LEFT:
                        return FACING_SOUTH;
                    case U:
                        return FACING_EAST;
                    default: return this;
                }
            default: return this;
        }

    }

    /**
     * Returns the opposite of the orientation.
     * @return Opposite of the original orientation.
     */
    public Orientation opposite(){
        switch (this){
            case FACING_NORTH:
                return FACING_SOUTH;
            case FACING_SOUTH:
                return FACING_NORTH;
            case FACING_EAST:
                return FACING_WEST;
            case FACING_WEST:
                return FACING_EAST;
            default:
                return this;
        }
    }

    /**
     * Get the value of which the sprite needs to be turned
     * @return The turn value
     */
    public int turnSprite(){
        switch (this){
            case FACING_SOUTH:
                return 180;
            case FACING_EAST:
                return 270;
            case FACING_WEST:
            case HORIZONTAL:
                return 90;
            default:
                return 0;
        }
    }

    /**
     * Finds the program needed to turn from one orientation to another
     * @param orientation The orientation to turn to
     * @return The program needed for the turn.
     */
    public Program turnNeeded(Orientation orientation){
        switch (this){
            case FACING_NORTH:
                switch (orientation){
                    case FACING_SOUTH:
                        return Program.U;
                    case FACING_EAST:
                        return Program.RIGHT;
                    case FACING_WEST:
                        return Program.LEFT;
                    default:
                        return Program.NONE;
                }
            case FACING_SOUTH:
                switch (orientation){
                    case FACING_NORTH:
                        return Program.U;
                    case FACING_EAST:
                        return Program.LEFT;
                    case FACING_WEST:
                        return Program.RIGHT;
                    default:
                        return Program.NONE;
                }
            case FACING_EAST:
                switch (orientation){
                    case FACING_NORTH:
                        return Program.LEFT;
                    case FACING_SOUTH:
                        return Program.RIGHT;
                    case FACING_WEST:
                        return Program.U;
                    default:
                        return Program.NONE;
                }
            case FACING_WEST:
                switch (orientation){
                    case FACING_NORTH:
                        return Program.RIGHT;
                    case FACING_SOUTH:
                        return Program.LEFT;
                    case FACING_EAST:
                        return Program.U;
                    default:
                        return Program.NONE;
                }
            default:
                return Program.NONE;
        }
    }
}

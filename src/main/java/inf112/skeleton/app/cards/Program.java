package inf112.skeleton.app.cards;

/**
 * Program enum
 */
public enum Program {
    MOVE1, MOVE2, MOVE3, MOVE4, BACK, BACK2, RIGHT, LEFT, U, NONE;

    /**
     * Returns the number a moves a program has
     * @return number of moves.
     */
    public int totalMoves(){
        switch (this){
            case NONE:
                return 0;
            case MOVE2:
            case BACK2:
                return 2;
            case MOVE3:
                return 3;
            case MOVE4:
                return 4;
            default: return 1;
        }
    }
    public boolean isMove(){
        switch (this){
            case MOVE1: return true;
            case MOVE2: return true;
            case MOVE3: return true;
            case BACK: return true;
            default: return false;
        }
    }

    @Override
    public String toString() {
        switch (this){
            case U: return "U Turn";
            case RIGHT: return "Turn Right";
            case LEFT: return "Turn Left";
            case MOVE1: return "Move 1";
            case MOVE2: return "Move 2";
            case MOVE3: return "Move 3";
            case BACK: return "Back Up";
            default: return "No Program";
        }
    }
}

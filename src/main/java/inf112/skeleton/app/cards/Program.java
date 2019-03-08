package inf112.skeleton.app.cards;

/**
 * Program enum
 */
public enum Program {
    MOVE1, MOVE2, MOVE3, BACK, RIGHT, LEFT, U, NONE;

    public int totalMoves(){
        switch (this){
            case MOVE1: return 1;
            case MOVE2: return 2;
            case MOVE3: return 3;
            default: return 0;
        }
    }
}

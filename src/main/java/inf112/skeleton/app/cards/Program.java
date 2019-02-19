package inf112.skeleton.app.cards;

/**
 * Program enum
 */
public enum Program {
    MOVE1, MOVE2, MOVE3, BACK, RIGHT, LEFT, U, NONE;

    public int totalMoves(){
        if(this.equals(Program.MOVE1)){
            return 1;
        }
        if(this.equals(Program.MOVE2)){
            return 2;
        }
        if(this.equals(Program.MOVE3)){
            return 3;
        }
        if(this.equals(Program.BACK)){
            return 1;
        }
        return 1;
    }
}

package inf112.skeleton.app.cards;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Movement Move;

    /**
     *
     * @param priority is the card priority for movement
     * @param move is the directioon magnitude or change
     */
    public ProgramCard(int priority, String move) {
        this.priority = priority;
        translateMove(move);
    }

    /** Get priority int
     */

    public int getPriority() {
        return priority;
    }

    /** Get Movement enum for card.
      */
    public Movement getMove() {
        return Move;
    }

    /**
     * Translate string to Movement
     * @param s description og movement to be translated.
     */
    private void translateMove (String s){
        switch (s) {
            case "U Turn":
                Move= Movement.U;
                break;
            case "Rotate Left":
                Move = Movement.LEFT;
                break;
            case "Rotate Right":
                Move = Movement.RIGHT;
                break;
            case "Back Up":
                Move = Movement.BACK;
                break;
            case "Move 1":
                Move = Movement.MOVE1;
                break;
            case "Move 2":
                Move = Movement.MOVE2;
                break;
            case "Move 3":
                Move = Movement.MOVE3;
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + s);
        }
    }


    @Override
    public void render() {

    }
}

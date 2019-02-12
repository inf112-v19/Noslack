package inf112.skeleton.app.cards;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Program Move;

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

    /** Get Program enum for card.
      */
    public Program getMove() {
        return Move;
    }

    /**
     * Translate string to Program
     * @param s description og movement to be translated.
     */
    private void translateMove (String s){
        switch (s) {
            case "U Turn":
                Move= Program.U;
                break;
            case "Rotate Left":
                Move = Program.LEFT;
                break;
            case "Rotate Right":
                Move = Program.RIGHT;
                break;
            case "Back Up":
                Move = Program.BACK;
                break;
            case "Move 1":
                Move = Program.MOVE1;
                break;
            case "Move 2":
                Move = Program.MOVE2;
                break;
            case "Move 3":
                Move = Program.MOVE3;
                break;
            default:
                throw new IllegalArgumentException("Invalid move: " + s);
        }
    }



    public void render() {

    }

    @Override
    public void evaluateSprite() {

    }
}

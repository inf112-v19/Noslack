package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Program Move;
    private Sprite sprite;
    private Texture texture;

    /**
     *
     * @param priority is the card priority for movement
     * @param move is the directioon magnitude or change
     */
    public ProgramCard(int priority, String move) {
        this.priority = priority;
        translateMove(move);
        evaluateSprite();
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


    @Override
    public void evaluateSprite() {
        /*
        * Todo:
        * Add cases for each Program enum to evaluate
        * into each texture.
        */
        switch(this.Move){

            default: this.texture = new Texture(Gdx.files.internal("./assets/error.png"));

        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}

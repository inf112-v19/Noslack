package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Program move;
    private Sprite sprite;
    private Texture texture;

    /**
     *
     * @param priority is the card priority for movement
     * @param move is the directioon magnitude or change
     */
    public ProgramCard(int priority, String move) {
        this.priority = priority;
        this.move=translateMove(move);
        // evaluateSprite();
    }

    /** Get priority int
     */
    public int getPriority() {
        return this.priority;
    }

    /** Get Program enum for card.
      */
    public Program getMove() {
        return this.move;
    }

    /**
     * Translate string to Program
     * @param s description og movement to be translated.
     */
    private Program translateMove (String s){
        switch (s) {
            case "U Turn":
                return Program.U;
            case "Rotate Left":
                return Program.LEFT;
            case "Rotate Right":
                return Program.RIGHT;
            case "Back Up":
                return Program.BACK;
            case "Move 1":
                return Program.MOVE1;
            case "Move 2":
                return Program.MOVE2;
            case "Move 3":
                return Program.MOVE3;
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
        switch(this.move){
            case U:

                break;
            case MOVE1:

                break;
            case MOVE2:

                break;
            case MOVE3:

                break;
            case BACK:

                break;
            case LEFT:

                break;

            case RIGHT:

                break;
            default: this.texture = new Texture(Gdx.files.internal("./assets/error.png"));

        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}

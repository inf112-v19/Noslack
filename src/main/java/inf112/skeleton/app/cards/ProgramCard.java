package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Program card lass that contains a priority and movement.
  */

public class ProgramCard implements RRCard {
    private int priority;
    private Program move;
    private Sprite sprite;
    private String name;
    private Vector2 position;

    /**
     *
     * @param priority is the card priority for movement
     * @param move is the directioon magnitude or change
     */
    public ProgramCard(int priority, String move) {
        this.priority = priority;
        this.move=translateMove(this.name=move);
    }

    /**
     * Only used for testing
     * @param priority priorty of card
     * @param move move the card is to have
     */
    public ProgramCard(int priority,Program move){
        this.priority = priority;
        this.move=move;
        this.name = move.toString();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
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
                setSprite("./assets/cards/u-turn.png");
                return Program.U;
            case "Rotate Left":
                setSprite("./assets/cards/r-left.png");
                return Program.LEFT;
            case "Rotate Right":
                setSprite("./assets/cards/r-right.png");
                return Program.RIGHT;
            case "Back Up":
                setSprite("./assets/cards/back-up2.png");
                return Program.BACK;
            case "Move 1":
                setSprite("./assets/cards/move-1.png");
                return Program.MOVE1;
            case "Move 2":
                setSprite("./assets/cards/move-2.png");
                return Program.MOVE2;
            case "Move 3":
                setSprite("./assets/cards/move-3.png");
                return Program.MOVE3;
            default:
                throw new IllegalArgumentException("Invalid move: " + s);
        }
    }

    /**
     * Sets the sprite for the the card.
     * @param filepath Filepath for Sprite
     */
    private void setSprite(String filepath){
        try {
            this.sprite = new Sprite(new Texture(Gdx.files.internal(filepath)));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(RRCard card) {
        return this.priority==((ProgramCard)card).getPriority();
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(getPriority(), ((ProgramCard) o).getPriority());
    }

    public String toString(){
        return this.name;
    }

}

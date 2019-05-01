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
    private Program program;
    private Sprite sprite;
    private String name;
    private Vector2 position;

    /**
     *
     * @param priority is the card priority for movement
     * @param program is the directioon magnitude or change
     */
    public ProgramCard(int priority, String program) {
        this.priority = priority;
        this.program =translateMove(this.name= program);
    }

    /**
     * Only used for testing
     * @param priority priorty of card
     * @param program program the card is to have
     */
    public ProgramCard(int priority,Program program){
        this.priority = priority;
        this.program = program;
        this.name = program.toString();
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
    public Program getProgram() {
        return this.program;
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
                setSprite("./assets/cards/back-up.png");
                return Program.BACK;
            case "Move 1":
                setSprite("./assets/cards/program-1.png");
                return Program.MOVE1;
            case "Move 2":
                setSprite("./assets/cards/program-1.png");
                return Program.MOVE2;
            case "Move 3":
                setSprite("./assets/cards/program-1.png");
                return Program.MOVE3;
            default:
                throw new IllegalArgumentException("Invalid program: " + s);
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

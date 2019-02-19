package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;

import java.util.ArrayList;
import java.util.Stack;

public class Player implements GameObject {
    private ArrayList<ProgramCard> programHand;
    private Stack<ProgramCard> program;
    private ArrayList<AbilityCard> abilityHand;
    private Texture texture;
    private Sprite sprite;
    private int health;
    private Orientation orientation;
    private int playerNumber;

    private Program currentMove;
    private int moveProgression;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber){
        this.health = 6;
        this.orientation = Orientation.FACING_NORTH;
        this.program = new Stack<>();
        this.programHand = new ArrayList<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.playerNumber = playerNumber;
        evaluateSprite();
    }



    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(Orientation orientation, int playerNumber){
        this.health = 6;
        this.orientation = orientation;
        this.program = new Stack<>();
        this.programHand = new ArrayList<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.playerNumber = playerNumber;
        evaluateSprite();
    }

    @Override
    public Sprite getSprite() {return sprite;}
    public int getHealth(){return health;}
    public Orientation getOrientation() {
        return orientation;
    }

    public void updateOrientation(Orientation orientation){this.orientation = orientation;}

    public void updateOrientation(Program rotation){
        this.orientation = Orientation.rotate(orientation, rotation);
        evaluateSprite();
    }

    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.PLAYER;}

    /**
     * Method that evaluates the player's sprite based on the player's orientation.
     */
    public void evaluateSprite(){
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/playerFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    /**
     * Give Cards to Player
     * @param AbilityCards IDeck of AbilityCards
     * @param ProgramCards IDeck of ProgramCards
     */
    public void drawCards(ArrayList<RRCard> ProgramCards, ArrayList<RRCard> AbilityCards){
        for (RRCard card:ProgramCards) this.programHand.add((ProgramCard) card);
        for (RRCard card:AbilityCards) this.abilityHand.add((AbilityCard) card);
    }
    // TODO take selected program from user interface
    private void pushProgram(ProgramCard[] selectedCards){
        for (int i =(selectedCards.length-1);i>=0;i--) {
            program.push(selectedCards[i]);
        }
    }

    /**
     * Removes one health from the player.
     */
    public void recieveDamage(){
        this.health--;
        }

    /**
     * Replenishes the players health by 1, up to a maximum of 9 (no damage).
     */
    public void repair(){
        if(health < 9)this.health++;
    }

    /**
     * Reset player for new round
     */
    public void reset(){
        programHand.clear();
        abilityHand.clear();
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    /**
     * @return Player AbilityDeck
     */
    public ArrayList<AbilityCard> getAbilityHand() {return abilityHand;}
    /**
     * @return Players ProgramDeck
     */
    public ArrayList<ProgramCard> getProgramHand() {return programHand;}
    /**
     * @return Program for round
     */
    public Stack<ProgramCard> getProgram() {return program;}

    public ProgramCard getNextProgram(){
        return this.program.pop();
    }

    public Program getCurrentMove(){
        return this.currentMove;
    }

    public void setCurrentMove(Program currentMove) {
        this.currentMove = currentMove;
    }

    public int getMoveProgression() {
        return moveProgression;
    }

    public void progressMove(){
        this.moveProgression++;
    }

    public void resetMoveProgress(){
        this.moveProgression = 0;
    }

    @Override
    public int compareTo(Object o) {
//        return Integer.compare(getPlayerNumber(),((Player) o).getPlayerNumber());
        return 0;
    }
}


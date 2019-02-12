package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;

import java.util.ArrayList;
import java.util.Stack;

public class Player implements GameObject {
    private ArrayList<ProgramCard> ProgramCards;
    private Stack<ProgramCard> Program;
    private ArrayList<AbilityCard> AbilityCards;
    private Texture texture;
    private Sprite sprite;
    private int health;
    private Orientation orientation;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(){
        this.health = 9;
        this.orientation = Orientation.FACING_NORTH;
        evaluateSprite();
    }

    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(Orientation orientation){
        this.health = 9;
        this.orientation = orientation;
        evaluateSprite();
    }

    @Override
    public Sprite getSprite() {return sprite;}
    public int getHealth(){return health;}
    public void updateOrientation(Orientation orientation){this.orientation = orientation;}
    @Override
    public int compareTo(Object other) {return 1; }
    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.PLAYER;}

    /**
     * Method that evaluates the player's sprite based on the player's orientation.
     */
    public void evaluateSprite(){
        if(this.orientation == Orientation.FACING_NORTH) {
            texture = new Texture(Gdx.files.internal("/assets/gameObjects/playerFacingNorth.png"));
        }
        if(this.orientation == Orientation.FACING_WEST){
            texture = new Texture(Gdx.files.internal("/assets/gameObjects/playerFacingWest.png"));
        }
        if(this.orientation == Orientation.FACING_SOUTH){
            texture = new Texture(Gdx.files.internal("/assets/gameObjects/playerFacingSouth.png"));
        }
        if(this.orientation == Orientation.FACING_EAST){
            texture = new Texture(Gdx.files.internal("/assets/gameObjects/playerFacingEast.png"));
        }

        this.sprite = new Sprite(texture);
    }

    /**
     * Give Cards to Player
     * @param AbilityCards IDeck of AbilityCards
     * @param ProgramCards IDeck of ProgramCards
     */
    public void drawCards(ArrayList<RRCard> ProgramCards, ArrayList<RRCard> AbilityCards){
        for (RRCard card:ProgramCards) this.ProgramCards.add((ProgramCard) card);
        for (RRCard card:AbilityCards)this.AbilityCards.add((AbilityCard) card);
    }

    /**
     * Removes one health from the player.
     */
    public void recieveDamage(){this.health--;}

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
        ProgramCards.clear();
        AbilityCards.clear();
    }

    /**
     * @return Player AbilityDeck
     */
    public ArrayList<AbilityCard> getAbilityCards() {return AbilityCards;}
    /**
     * @return Players ProgramDeck
     */
    public ArrayList<ProgramCard> getProgramCards() {return ProgramCards;}
    /**
     * @return Program for round
     */
    public Stack<ProgramCard> getProgram() {return Program;}
}

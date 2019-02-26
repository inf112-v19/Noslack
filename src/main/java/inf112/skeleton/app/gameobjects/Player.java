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
    private Coordinate backUp;
    private GameObjectType type;
    private Coordinate position;

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
        this.type = GameObjectType.PLAYER;
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
        texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/player.png"));

        this.sprite = new Sprite(texture);
        switch (orientation) {
            default:
                sprite.rotate(0);
                break;
            case FACING_NORTH:
                sprite.rotate(0);
                break;
            case FACING_EAST:
                sprite.rotate(90);
                break;
            case FACING_WEST:
                sprite.rotate(270);
                break;
            case FACING_SOUTH:
                sprite.rotate(180);
                break;
        }
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
    public void pushProgram(ArrayList<ProgramCard> selectedCards){
        program.clear();
        program.addAll(selectedCards);
        System.out.println(program);
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
    public void setBackUp(Coordinate backUp){
        this.backUp=backUp;
    }

    public Coordinate getBackUp() {
        return backUp;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
    public Coordinate getPosition() {
        return position;
    }

    @Override
    public int compareTo(Object o) {
//        return Integer.compare(getPlayerNumber(),((Player) o).getPlayerNumber());
        return 1;
    }
}


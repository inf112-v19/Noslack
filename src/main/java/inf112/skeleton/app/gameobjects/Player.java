package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class Player implements GameObject {
    private ArrayList<ProgramCard> programHand;
    private Stack<ProgramCard> program;
    private ArrayList<AbilityCard> abilityHand;
    private Sprite sprite;
    private int health;
    private Orientation orientation;
    private int playerNumber;
    private Coordinate backUp;
    private Coordinate position;
    private String name;
    private boolean hasWon;
    private ArrayList<Integer> flagsVisited;
    private Program currentMove;
    private int moveProgression;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber){
        this.health = 9;
        this.orientation = Orientation.FACING_NORTH;
        this.program = new Stack<>();
        this.programHand = new ArrayList<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.playerNumber = playerNumber;
        this.hasWon = false;
        this.name = "RoboHally";
        this.flagsVisited = new ArrayList<Integer>(9);
        evaluateSprite();
    }

    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber,Orientation orientation){
        this.health = 9;
        this.orientation = orientation;
        this.program = new Stack<>();
        this.programHand = new ArrayList<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.playerNumber = playerNumber;
        this.hasWon = false;
        this.name = "RoboHally";
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/player32x32.png"));

            this.sprite = new Sprite(texture);
            switch (orientation) {
                default:
                    sprite.setRotation(0);
                    break;
                case FACING_NORTH:
                    sprite.setRotation(0);
                    break;
                case FACING_EAST:
                    sprite.setRotation(270);
                    break;
                case FACING_WEST:
                    sprite.setRotation(90);
                    break;
                case FACING_SOUTH:
                    sprite.setRotation(180);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Player evaluateSprite");
        }
    }

    @Override
    public Sprite getSprite() {return sprite;}

    /**
     * Get the players number
     * @return The players number.
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Get method for health
     * @return Players health
     */
    public int getHealth(){
        return health;
    }

    /**
     * Removes one health from the player.
     */
    public void receiveDamage(){
        this.health--;
    }

    /**
     * Remove given amount of health from player
     * @param damage amount of health to be deducted
     */
    public void receiveDamage(int damage){
        this.health -= damage;
    }

    /**
     * Give player a new orientation
     * @param orientation The players new orientation
     */
    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
        evaluateSprite();
    }

    @Override
    public Orientation getOrientation() {return orientation;}

    /**
     * Method for updating orientation
     * @param rotation ??
     */
    public void updateOrientation(Program rotation){
        this.orientation = orientation.rotate(rotation);
        evaluateSprite();
    }

    /**
     * Set a new psoition for the player
     * @param position New position
     */
    public void setPosition(Coordinate position) {
        this.position =position;
    }

    /**
     * Get the players position
     * @return Players current position
     */
    public Coordinate getPosition() {
        return this.position;
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

    /**
     * @return Player AbilityDeck
     */
    public ArrayList<AbilityCard> getAbilityHand() {return abilityHand;}

    /**
     * @return Players ProgramDeck
     */
    public ArrayList<ProgramCard> getProgramHand() {return programHand;}

    /**
     * Push the players chosen program into a queue
     * @param selectedCards The selected programs
     */
    public void pushProgram(ArrayList<ProgramCard> selectedCards){
        this.program.clear();

        for (int i = (selectedCards.size()-1); i >=0; i--) {
            this.program.push(selectedCards.get(i));
        }
        //this.program.addAll(selectedCards);
    }

    /**
     * @return Program for round
     */
    public Stack<ProgramCard> getProgram() {return this.program;}

    /**
     * Set the players next program from the program stack.
     */
    public void setNextProgram(){
        this.currentMove = this.program.pop().getMove();
    }

    /**
     * Set the next program to be executed
     * @param currentMove The current Program to be executed
     */
    public void setCurrentMove(Program currentMove) {
        this.currentMove = currentMove;
    }

    /**
     * Get the players current program.
     * @return The current program.
     */
    public Program getCurrentMove(){
        return this.currentMove;
    }

    /**
     * Replenishes the players health by 1, up to a maximum of 9 (no damage).
     */
    public void repair(){
        if(health < 9)this.health++;
    }

    public ArrayList<Integer> getFlagsVisited(){
        return flagsVisited;
    }

    public void initiate (Coordinate cor){
        setPosition(cor);
        setBackUp();
    }

    /**
     * Get the move progression for the players current program
     * @return How far the player has gotten in the move.
     */
    public int getMoveProgression() {
        return moveProgression;
    }

    /**
     * Progress the players current move.
     */
    public void progressMove(){
        this.moveProgression++;
    }

    /**
     * Stop the current program.
     */
    public void resetMoveProgress(){
        this.moveProgression = 0;
    }

    /**
     * Reset player for new round
     */
    public void reset(){
        this.programHand.clear();
        this.abilityHand.clear();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    public void setBackUp(Coordinate backUp){
        this.backUp=backUp;
    }


    /**
     * Set the players Back Up
     */
    public void setBackUp(){
        this.backUp = this.getPosition();
        this.backUp.setOrientation(getOrientation());
    }

    /**
     * Get the players Back Up
     * @return the players Back Up
     */
    public Coordinate getBackUp(){
        return this.backUp;
    }

    public void setFlagsVisited(int n){
        this.flagsVisited = new ArrayList<Integer>(Collections.nCopies(n,0));
    }

    public boolean isFinished(){
        return this.program.isEmpty();
    }

    public void win(){
        if(!this.hasWon){
            System.out.println(this.name + " HAS WON!");
            this.hasWon = true;
        }
    }

    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.PLAYER;}

    @Override
    public int compareTo(Object o) {
        // return Integer.compare(getPlayerNumber(),((Player) o).getPlayerNumber());
        return 1;
    }
}


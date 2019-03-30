package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;

import java.util.ArrayList;
import java.util.Stack;

public class Player implements IRobot {
    private ArrayList<ProgramCard> programHand;
    private Stack<ProgramCard> program;
    private ArrayList<AbilityCard> abilityHand;
    private Sprite sprite;
    private int health;
    private int lives;
    private Orientation orientation;
    private int playerNumber;
    private Coordinate backUp;
    private Coordinate position;
    private String name;
    private boolean hasWon;
    private boolean[] flagsVisited;
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
        this.lives = 3;
        this.orientation = Orientation.FACING_NORTH;
        this.program = new Stack<>();
        this.programHand = new ArrayList<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.playerNumber = playerNumber;
        this.hasWon = false;
        this.name = "RoboHally";
        evaluateSprite();
    }

    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber,Orientation orientation){
        this.health = 9;
        this.lives = 3;
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
            turnSprite();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Player evaluateSprite");
        }
    }

    /**
     * Turns the sprite based on the objects orientation
     */
    private void turnSprite(){
        try {
            sprite.setRotation(this.orientation.turnSprite());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Player turnSprite");
        }
    }

    @Override
    public Sprite getSprite() {return sprite;}

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public int getHealth(){
        return health;
    }

    @Override
    public void receiveDamage(){
        this.health--;
    }

    @Override
    public void receiveDamage(int damage){
        this.health -= damage;
    }

    @Override
    public int getLives(){
        return this.lives;
    }

    /**
     * Take a live from player when respawning
     */
    private void takeLives(){
        this.lives--;
    }

    @Override
    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
        turnSprite();
    }

    @Override
    public Orientation getOrientation() {return orientation;}

    @Override
    public void updateOrientation(Program rotation){
        this.orientation = orientation.rotate(rotation);
        turnSprite();
    }

    @Override
    public void setPosition(Coordinate position) {
        this.position =position;
    }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public void drawCards(ArrayList<RRCard> ProgramCards, ArrayList<RRCard> AbilityCards){
        for (RRCard card:ProgramCards) this.programHand.add((ProgramCard) card);
        for (RRCard card:AbilityCards) this.abilityHand.add((AbilityCard) card);
    }

    @Override
    public ArrayList<AbilityCard> getAbilityHand() {return abilityHand;}

    @Override
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
    }

    @Override
    public Stack<ProgramCard> getProgram() {return this.program;}

    @Override
    public void setNextProgram(){
        if(!this.program.isEmpty())
            this.currentMove = this.program.pop().getMove();
    }

    @Override
    public void setCurrentMove(Program currentMove) {
        this.currentMove = currentMove;
    }

    @Override
    public Program getCurrentMove(){
        return this.currentMove;
    }

    @Override
    public void repair(){
        if(health < 9)this.health++;
    }

    @Override
    public void setFlagsVisitedSize(int n){
        this.flagsVisited = new boolean[n];
        for(int i =0;i<this.flagsVisited.length;i++){
            this.flagsVisited[i] = false;
        }
    }

    @Override
    public boolean[] getFlagsVisited(){
        return flagsVisited;
    }

    @Override
    public void visitFlag(int n){
        this.flagsVisited[n-1]=true;
    }

    @Override
    public boolean getFlag(int n){
        if(n<1)
            n=1;
       return this.flagsVisited[n-1];
    }

    @Override
    public void initiate (Coordinate cor){
        setPosition(cor);
        setBackUp();
    }

    @Override
    public int getMoveProgression() {
        return moveProgression;
    }

    @Override
    public void progressMove(){
        this.moveProgression++;
    }

    @Override
    public void resetMoveProgress(){
        this.moveProgression = 0;
    }

    @Override
    public void reset(){
        this.programHand.clear();
        this.abilityHand.clear();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    @Override
    public void respawn(){
        reset();
        takeLives();
        setPosition(this.backUp);
        setOrientation(this.backUp.getOrientation());
    }

    @Override
    public void setBackUp(){
        this.backUp = this.getPosition();
        this.backUp.setOrientation(getOrientation());
    }

    @Override
    public Coordinate getBackUp(){
        return this.backUp;
    }

    @Override
    public boolean isFinished(){
        return this.program.isEmpty();
    }

    @Override
    public void win(){
        if(!this.hasWon){
            System.out.println(this.name + " HAS WON!");
            this.hasWon = true;
        }
    }

    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.PLAYER;}

    @Override
    public String toString(){
        return this.playerNumber + this.name;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}


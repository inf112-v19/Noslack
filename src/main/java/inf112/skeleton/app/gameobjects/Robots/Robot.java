package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.AbilityCard;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;
import java.util.Stack;


public abstract class Robot implements IRobot{
    String name;
    int playerNumber;
    Sprite sprite;
    Orientation orientation;
    Coordinate position;
    int lives;
    int health;
    Program currentMove;
    private Coordinate backUp;
    private int moveProgression;
    ArrayList<AbilityCard> abilityHand;
    Stack<ProgramCard> program;
    boolean hasWon;
    private boolean[] flagsVisited;

    @Override
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Turns the sprite based on the objects orientation
     */
    void turnSprite(){
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
    public int getHealth(){
        return health;
    }

    private void setHealth(int health){
        this.health = health;
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
    public Orientation getOrientation() {
        return orientation;
    }

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
        return position;
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
    public void win(){
        if(!this.hasWon){
            System.out.println(this.name + " HAS WON!");
            this.hasWon = true;
        }
    }

    @Override
    public int getNextProgramPriority(){
        return program.peek().getPriority();
    }

    @Override
    public void setNextProgram(){
        if(!this.program.isEmpty())
            this.currentMove = this.program.pop().getMove();
    }

    @Override
    public Stack<ProgramCard> getProgram() {return this.program;}

    @Override
    public void setCurrentMove(Program currentMove) {
        this.currentMove = currentMove;
    }

    @Override
    public Program getCurrentMove(){
        return this.currentMove;
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
    public void repair(){
        if(health < 9)this.health++;
    }

    @Override
    public void respawn(){
        reset();
        takeLives();
        setHealth(7);
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
    public void initiate (Coordinate cor){
        setPosition(cor);
        setBackUp();
    }

    @Override
    public boolean isFinished(){
        return this.program.isEmpty();
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}
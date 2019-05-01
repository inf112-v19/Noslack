package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;
import java.util.Stack;


public abstract class Robot implements IRobot{
    String name;
    int robotNumber;
    Sprite sprite;
    Orientation orientation;
    Coordinate position;
    int lives;
    int health;
    Program currentMove;
    private Coordinate backUp;
    private int moveProgression;
    ArrayList<AbilityCard> abilityHand;
    boolean activeAbility;
    Stack<ProgramCard> program;
    boolean hasWon;
    private boolean[] flagsVisited;
    private boolean hasMoved;
    boolean powerDown;

    public void create(int robotNumber,Orientation orientation, Coordinate position){
        this.orientation = orientation;
        this.position = position;
        this.robotNumber = robotNumber;
        this.name = "Robot " + robotNumber;
        this.health = 9;
        this.lives = 3;
        this.program = new Stack<>();
        this.abilityHand = new ArrayList<>();
        this.currentMove = Program.NONE;
        this.hasWon = false;
        this.powerDown = false;
    }

    @Override
    public void drawAbility(RRCard abilityCard) {
        this.abilityHand.add((AbilityCard) abilityCard);
    }

    @Override
    public void discardAbility(RRCard card){
        this.abilityHand.remove(card);
    }

    @Override
    public int getRobotNumber() {
        return this.robotNumber;
    }

    /**
     * Turns the sprite based on the objects orientation
     */
    void turnSprite(){
        try {
            sprite.setRotation(this.orientation.turnSprite());
            this.position.setOrientation(this.orientation);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in Robot turnSprite");
        }
    }

    @Override
    public Sprite getSprite() {return sprite;}

    @Override
    public boolean hasMoved(){
        return hasMoved;
    }
    @Override
    public void moved(boolean bol){
        this.hasMoved = bol;
    }


    @Override
    public int getHealth(){
        return health;
    }

    private void setHealth(int health){
        this.health = health;
    }

    @Override
    public boolean receiveDamage(){
        return this.receiveDamage(1);
    }

    @Override
    public boolean receiveDamage(int damage){
        this.health -= damage;
        if(this.health<=0){
            this.health=9;
            return true;
        }
        return false;
    }

    @Override
    public int getLives(){
        return this.lives;
    }

    /**
     * Take a live from robot when respawning
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
    public boolean win(){
        if(flagsVisited[flagsVisited.length-1]){
            System.out.println(this.name + " HAS WON!");
            this.hasWon = true;
        }
        return hasWon;
    }

    @Override
    public boolean dead(){
        return this.lives <1;
    }

    @Override
    public int getNextProgramPriority(){
        if(this.program.isEmpty()){
            return 0;
        }
        else {
            return program.peek().getPriority();
        }
    }

    @Override
    public void setNextProgram(){
        if(!this.program.isEmpty())
            this.currentMove = this.program.pop().getMove();
        applyAbilityToMoves();
    }

    @Override
    public void applyAbilityToMoves(){
        if(currentMove == Program.BACK &&  hasAbility(Ability.ReverseGear)){
            currentMove = Program.BACK2;
        }
        if(currentMove == Program.MOVE3 && hasAbility(Ability.FourthGear)){
            currentMove = Program.MOVE4;
        }
        if(currentMove == Program.MOVE1 && hasAbility(Ability.Brakes)){
            currentMove = Program.NONE;
        }
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
    public boolean hasAbility(Ability ability){
        for(RRCard card : abilityHand){
            if(((AbilityCard)card).getAbility().equals(ability)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void activateAbility(){
        this.activeAbility = !activeAbility;
    }
    @Override
    public boolean getActiveAbility(){
        return this.activeAbility;
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
        if(hasAbility(Ability.SuperiorArchive)){
            setHealth(10);
        }else {
            setHealth(7);
        }
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
    public void powerDown(){
        this.powerDown = !this.powerDown;
    }

    @Override
    public boolean isPoweredDown(){
        return this.powerDown;
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

    @Override
    public GameObjectType getGameObjectType() {
        return GameObjectType.ROBOT;
    }
}
package inf112.skeleton.app.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;

import java.util.Stack;


public abstract class Robot implements IRobot{
    Sprite sprite;
    Orientation orientation;
    Coordinate position;
    int lives;
    int health;
    Program currentMove;

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

    private void setHealth(int health){
        this.health=health;
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

    private void setLives(int n){
        this.lives = n;
    }

    @Override
    public int getLives(){
        return this.lives;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Coordinate getPosition() {
        return position;
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
}

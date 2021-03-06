package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;

public class Player extends Robot {
    public String selectedSprite = "./assets/gameObjects/player/player32x32.png";
    private ArrayList<ProgramCard> programHand;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber){
        this(playerNumber, Orientation.FACING_NORTH, new Coordinate(0,0));
    }
    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber, Orientation orientation, Coordinate position){
        create(playerNumber, orientation, position);
        this.name = "Player";
        this.programHand = new ArrayList<>();
        evaluateSprite();
    }

    public void setSelectedSprite(String sprite){
        this.selectedSprite = sprite;
        evaluateSprite();
    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture = new Texture(Gdx.files.internal(this.selectedSprite));
            this.sprite = new Sprite(texture);
            turnSprite();
        } catch (Exception e) {
        }
    }

    @Override
    public void drawPrograms(ArrayList<RRCard> programCards){
        for (RRCard card: programCards) this.programHand.add((ProgramCard) card);
    }

    @Override
    public void extraCard(RRCard card) {
        this.programHand.add((ProgramCard)card);
    }

    @Override
    public ArrayList<AbilityCard> getAbilityHand() {return abilityHand;}

    @Override
    public ArrayList<ProgramCard> getProgramHand() {return programHand;}

    @Override
    public void pushProgram(ArrayList<ProgramCard> selectedCards){
        this.program.clear();
        for (int i = (selectedCards.size()-1); i >=0; i--) {
            this.program.push(selectedCards.get(i));
        }
        if(this.powerDown){
            this.program.clear();
            ProgramCard card = new ProgramCard(0, Program.NONE);
            for(int i =0; i<5; i++){
                this.program.push(card);
            }
        }
    }

    @Override
    public void reset(){
        powerUp();
        this.programHand.clear();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    @Override
    public String toString(){
        return this.robotNumber + ":" + this.name;
    }

    @Override
    public boolean isAI(){
        return false;
    }
}
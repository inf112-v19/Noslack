package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;
import java.util.Stack;

public class Player extends Robot {
    private ArrayList<ProgramCard> programHand;

    /**
     * Constructor of Player class.
     * Initialises health to 9.
     * Initialises orientation to FACING_NORTH
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber){
        this(playerNumber, Orientation.FACING_NORTH);
    }
    /**
     * Constructor of Player class with orientation specified.
     * Initialises health to 9.
     * Evaluates sprite based on orientation.
     */
    public Player(int playerNumber,Orientation orientation){
        create();
        this.name = "Player";
        this.orientation = orientation;
        this.programHand = new ArrayList<>();
        this.playerNumber = playerNumber;
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
    }

    @Override
    public void reset(){
        this.programHand.clear();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.ROBOT;}

    @Override
    public String toString(){
        return this.playerNumber + ":" + this.name;
    }
}
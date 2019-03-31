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
    public void reset(){
        this.programHand.clear();
        this.abilityHand.clear();
        this.program.clear();
        this.currentMove=Program.NONE;
        resetMoveProgress();
    }

    @Override
    public GameObjectType getGameObjectType() {return GameObjectType.PLAYER;}

    @Override
    public String toString(){
        return this.playerNumber + this.name;
    }
}


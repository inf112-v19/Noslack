package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;

public class HunterAI extends AI {
    private GameObjectType type;
    private ArrayList<AbilityCard> abilityHand;

    public HunterAI(int playerNumber){
        this(playerNumber,Orientation.FACING_NORTH);
    }

    public HunterAI(int playerNumber, Orientation orientation){
        create();
        this.name = "HunterAI";
        this.playerNumber = playerNumber;
        this.orientation = orientation;
        this.abilityHand = new ArrayList<>();

        this.type=GameObjectType.ROBOT;

        evaluateSprite();
    }

    @Override
    public void drawCards(ArrayList<RRCard> ProgramCards, ArrayList<RRCard> AbilityCards) {
        this.ProgramHand = new AIHand(ProgramCards);
        for(RRCard card : AbilityCards){
            this.abilityHand.add((AbilityCard) card);
        }
    }

    @Override
    public ArrayList<AbilityCard> getAbilityHand() {
        return null;
    }

    @Override
    public ArrayList<ProgramCard> getProgramHand() {
        return this.ProgramHand.getHand();
    }

    @Override
    public void reset() {

    }

    @Override
    public void evaluateSprite() {
        try {
            Texture texture = new Texture(Gdx.files.internal("./assets/gameObjects/player/robot32x32.png"));

            this.sprite = new Sprite(texture);
            turnSprite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString(){
        return this.playerNumber + ":" + this.name;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
    }


}

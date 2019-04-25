package inf112.skeleton.app.gameobjects.Robots;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObjectType;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;

public class HunterAI extends AI {
    private ArrayList<AbilityCard> abilityHand;

    public HunterAI(int playerNumber){
        this(playerNumber,Orientation.FACING_NORTH);
    }

    public HunterAI(int playerNumber, Orientation orientation){
        create(playerNumber, orientation);
        this.abilityHand = new ArrayList<>();

        evaluateSprite();
    }

    @Override
    public void drawPrograms(ArrayList<RRCard> programCards) {
        this.programHand = new AIHand(programCards);

    }

    @Override
    public void extraCard(RRCard card) {
        this.programHand.addExtraCard(card);
    }

    @Override
    public ArrayList<AbilityCard> getAbilityHand() {
        return null;
    }

    @Override
    public ArrayList<ProgramCard> getProgramHand() {
        return this.programHand.getHand();
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
}

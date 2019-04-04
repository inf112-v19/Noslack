package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.GameObjectType;

import java.util.ArrayList;

public class HunterAI extends AI {
    private GameObjectType type;
    private ArrayList<AbilityCard> abilityHand;

    public HunterAI(){
        this.type=GameObjectType.HUNTER;
        this.abilityHand = new ArrayList<>();
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

    }

    @Override
    public GameObjectType getGameObjectType() {
        return this.type;
    }
}

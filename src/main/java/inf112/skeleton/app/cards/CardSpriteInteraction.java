package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CardSpriteInteraction {
    private ArrayList<RRCard> chosenCards;

    private ArrayList<Vector2> cardSlotPositions;

    private int intOffset = 15;

    private Vector2 cardOffset = new Vector2(74+intOffset,115+intOffset);

    private CardSpriteInteraction(ArrayList<RRCard> chosenCards){
        this.chosenCards = chosenCards;
        cardSlotPositions.add(new Vector2(80,44));
        cardSlotPositions.add(new Vector2(187,44));
        cardSlotPositions.add(new Vector2(293,44));
        cardSlotPositions.add(new Vector2(400,44));
        cardSlotPositions.add(new Vector2(508,44));

    }

    private CardSpriteInteraction() {
        cardSlotPositions.add(new Vector2(80,44));
        cardSlotPositions.add(new Vector2(187,44));
        cardSlotPositions.add(new Vector2(293,44));
        cardSlotPositions.add(new Vector2(400,44));
        cardSlotPositions.add(new Vector2(508,44));
    }

    /**
     * Checks if a card is inside a slot and sends the RRCard to the ArrayList
     * @param card that is to be validated
     * @param screenX
     * @param screenY must be flipped! (Gdx.graphics.getHeight()-screenY)
     * @return whether or not the card is inside a slot
     */
    public boolean cardPositionValidation(RRCard card, float screenX, float screenY){
        for(int i = 0; i < cardSlotPositions.size(); i++){
            if (insideSlot(screenX,screenY,cardSlotPositions.get(i).x,cardSlotPositions.get(i).y)){
                chosenCards.add(i,card);
                return true;
            }
        }
        return false;
    }

    private boolean insideSlot(float screenX, float screenY, float slotX, float slotY){
        if (screenX > slotX-cardOffset.x/2 && screenX < slotX+cardOffset.x/2){
            if (screenY > slotY-cardOffset.y/2 && screenY < slotY-cardOffset.y/2){
                return true;
            }
        }
        return false;
    }

    public ArrayList<RRCard> getChosenCards(){
        return chosenCards;
    }
}

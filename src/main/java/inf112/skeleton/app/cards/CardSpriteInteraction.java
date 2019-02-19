package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class CardSpriteInteraction {

    private ProgramCard[] cardSequence;

    private ArrayList<ProgramCard> chosenCards;

    private ArrayList<Vector2> cardSlotPositions;

    private int intOffset = 15;

    private Vector2 cardOffset = new Vector2(74+intOffset,115+intOffset);

    public CardSpriteInteraction(ArrayList<ProgramCard> chosenCards){
        this.chosenCards = chosenCards;
        cardSlotPositions = new ArrayList<>();
        cardSlotPositions.add(new Vector2(80,44));
        cardSlotPositions.add(new Vector2(187,44));
        cardSlotPositions.add(new Vector2(293,44));
        cardSlotPositions.add(new Vector2(400,44));
        cardSlotPositions.add(new Vector2(508,44));

    }

    public CardSpriteInteraction() {
        this.cardSequence = new ProgramCard[5];
        this.chosenCards = new ArrayList<>();
        cardSlotPositions = new ArrayList<>();
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
    public boolean cardPositionValidation(ProgramCard card, float screenX, float screenY){
        for(int i = 0; i < cardSlotPositions.size(); i++){
            if (insideSlot(screenX,screenY,cardSlotPositions.get(i).x,cardSlotPositions.get(i).y)){
                chosenCards.add(i,card);
                return true;
            }
        }
        return false;
    }

    public void setCardSlot(ProgramCard card, int slot){
        cardSequence[slot] = card;
        System.out.println("Card "+ card.toString() + " was added to slot "+ (slot+1));
    }

    /**
     *
     * CHANGE THIS METHOD :)
     * @param screenX
     * @param screenY
     * @return
     */
    public Vector2 cardSnapPosition(ProgramCard card, float screenX, float screenY){
        for(int i = 0; i < cardSlotPositions.size(); i++){
            float xDiff = screenX-cardSlotPositions.get(i).x;
            float yDiff = screenY-cardSlotPositions.get(i).y;

            if(Math.abs(xDiff) < 50){
                if(Math.abs(yDiff) < 70){
                    setCardSlot(card, i);
                    return cardSlotPositions.get(i);
                }
            }
        }
        return new Vector2(100,300);
    }

    private boolean insideSlot(float screenX, float screenY, float slotX, float slotY){
        if (screenX > slotX-cardOffset.x/2 && screenX < slotX+cardOffset.x/2){
            if (screenY > slotY-cardOffset.y/2 && screenY < slotY-cardOffset.y/2){
                return true;
            }
        }
        return false;
    }

    public ArrayList<ProgramCard> getChosenCards(){
        return chosenCards;
    }
}

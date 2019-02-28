package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;

import javax.xml.bind.SchemaOutputResolver;
import java.util.ArrayList;

public class CardSpriteInteraction {

    private ProgramCard[] cardSequence;

    private ArrayList<ProgramCard> chosenCards;

    private ArrayList<Vector2> cardSlotPositions;

    private int intOffset = 15;

    private Vector2 cardOffset = new Vector2(74+intOffset,115+intOffset);

    private ProgramCard empty= new ProgramCard(0,Program.NONE);

    private ProgramCard overlappingCard;

    public CardSpriteInteraction(ArrayList<ProgramCard> chosenCards){

        this.chosenCards = chosenCards;
        this.cardSlotPositions = new ArrayList<>();
        this.cardSlotPositions.add(new Vector2(80,44));
        this.cardSlotPositions.add(new Vector2(187,44));
        this.cardSlotPositions.add(new Vector2(293,44));
        this.cardSlotPositions.add(new Vector2(400,44));
        this.cardSlotPositions.add(new Vector2(508,44));

    }

    public CardSpriteInteraction() {
        this.overlappingCard = this.empty;
        this.chosenCards = new ArrayList<>();

        reset();

        this.cardSlotPositions = new ArrayList<>();
        this.cardSlotPositions.add(new Vector2(80,44));
        this.cardSlotPositions.add(new Vector2(187,44));
        this.cardSlotPositions.add(new Vector2(293,44));
        this.cardSlotPositions.add(new Vector2(400,44));
        this.cardSlotPositions.add(new Vector2(508,44));
    }

    /**
     * Checks if a card is inside a slot and sends the RRCard to the ArrayList
     * @param card that is to be validated
     * @param screenX
     * @param screenY must be flipped! (Gdx.graphics.getHeight()-screenY)
     * @return whether or not the card is inside a slot
     */
    public boolean cardPositionValidation(ProgramCard card, float screenX, float screenY){
        for(int i = 0; i < this.cardSlotPositions.size(); i++){
            if (insideSlot(screenX,screenY,this.cardSlotPositions.get(i).x,this.cardSlotPositions.get(i).y)){
                this.chosenCards.add(i,card);
                return true;
            }
        }
        return false;
    }

    public void setCardSlot(ProgramCard card, int slot){
        if(this.chosenCards.contains(card)){
            int oldSlot = this.chosenCards.indexOf(card);
            removeCard(oldSlot);
        }
        if(this.chosenCards.get(slot) != this.empty){
            this.chosenCards.get(slot).setPosition(this.chosenCards.get(slot).getPosition());
        }
        this.chosenCards.remove(slot);
        this.chosenCards.add(slot,card);
    }

    public void removeCard(int slot){
        this.chosenCards.remove(slot);
        this.chosenCards.add(slot,this.empty);
    }

    /**
     *
     * CHANGE THIS METHOD :)
     * @param screenX
     * @param screenY
     * @return
     */
    public Vector2 cardSnapPosition(ProgramCard card, float screenX, float screenY){
        for(int i = 0; i < this.cardSlotPositions.size(); i++){
            float xDiff = screenX-this.cardSlotPositions.get(i).x;
            float yDiff = screenY-this.cardSlotPositions.get(i).y;

            if(Math.abs(xDiff) < 50){
                if(Math.abs(yDiff) < 70){
                    if(this.chosenCards.get(i) != this.empty){
                        this.overlappingCard = this.chosenCards.get(i);
                    }
                    setCardSlot(card, i);
                    Vector2 pos = new Vector2(this.cardSlotPositions.get(i).x-74,this.cardSlotPositions.get(i).y);
                    return pos;
                }
            }
        }
        if(this.chosenCards.contains(card)){
            int slot = this.chosenCards.indexOf(card);
            removeCard(slot);
        }
        return card.getPosition();
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
        return this.chosenCards;
    }

    public ProgramCard getCardOverlap(){
        ProgramCard card = this.overlappingCard;
        this.overlappingCard = this.empty;
        return card;
    }

    public void reset(){
        this.chosenCards.clear();
        for (int i = 0; i < 5; i++){
            this.chosenCards.add(i,this.empty);
        }
    }
}

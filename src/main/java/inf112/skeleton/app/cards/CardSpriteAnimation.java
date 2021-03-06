package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.SoundContainer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardSpriteAnimation {

    private ArrayList<ProgramCard> hand;
    private int i;
    private int j;
    private Vector2 startPos;
    private Vector2 endPos;
    private SoundContainer sounds;
    private boolean isDone;

    public CardSpriteAnimation(ArrayList<ProgramCard> hand){
        this.sounds = new SoundContainer();

        this.hand=hand;
        this.startPos = new Vector2(730,400);
        this.endPos = new Vector2(0,565);

        this.i = 0;
        this.j = 0;
        for (ProgramCard card : hand){
            try {
                card.getSprite().setPosition(startPos.x, startPos.y);
            }catch (Exception e){
                System.out.println("Error in CardSpriteAnimation");
            }
        }

    }

    public ArrayList<ProgramCard> updatePositions(){
        isDone = false;
        shuffleLeft();
        return hand;
    }

    public ArrayList<ProgramCard> finishAnimation(){
        for (int k = 0; k < hand.size(); k++){
            ProgramCard card = hand.get(k);
            card.getSprite().setPosition((float)25+k*80,endPos.y);
            card.setPosition(new Vector2((float)25+k*80,endPos.y));
        }
        return hand;
    }

    private void shuffleLeft(){
        if(i<hand.size()){
            double end = 25+i*80;
            int x = (int)(startPos.x - j);
            double progress = (j/(startPos.x-end));
            //double deltaY = (0.5-Math.abs(progress - 0.5));
            double deltaY = 0;
            int y = (int)(progress*endPos.y + (1-progress)*startPos.y - (deltaY*200));

            Vector2 pos = new Vector2(x,y);
            ProgramCard card = hand.get(i);
            try {
                card.getSprite().setPosition(pos.x, pos.y);
            }catch (Exception e){
                System.out.println("Error in shuffleLeft line 54");
            }
            card.setPosition(pos);
            hand.set(i,card);
            j+=10;

            if(x <= end){
                try {
                    sounds.shuffleCardSound();
                    card.getSprite().setPosition((float) end, endPos.y);
                    card.setPosition(new Vector2((float) end, endPos.y));
                    i++;
                    j = 0;
                }catch (Exception e){
                    System.out.println("Error in shuffleLaft line 64");
                }
            }

        } else {
            isDone = true;
        }
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }
}

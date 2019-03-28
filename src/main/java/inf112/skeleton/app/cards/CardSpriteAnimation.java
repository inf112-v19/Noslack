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

    public CardSpriteAnimation(ArrayList<ProgramCard> hand){
        this.sounds = new SoundContainer();

        this.hand=hand;
        this.startPos = new Vector2(730,400);
        this.endPos = new Vector2(0,555);

        this.i = 0;
        this.j = 0;
        for (ProgramCard card : hand){
            card.getSprite().setPosition(startPos.x,startPos.y);
        }

    }

    public ArrayList<ProgramCard> updatePositions(){
        shuffleLeft();
        return hand;
    }

    private void shuffleLeft(){

        if(i<hand.size()){
            double end = 25+i*75;
            int x = (int)(startPos.x - j);
            double progress = (j/(startPos.x-end));
            //double deltaY = (0.5-Math.abs(progress - 0.5));
            double deltaY = 0;
            int y = (int)(progress*endPos.y + (1-progress)*startPos.y - (deltaY*200));

            Vector2 pos = new Vector2(x,y);
            ProgramCard card = hand.get(i);
            card.getSprite().setPosition(pos.x,pos.y);
            card.setPosition(pos);
            hand.set(i,card);
            j+=10;

            if(x <= end){
                sounds.shuffleCardSound();
                card.getSprite().setPosition((float)end,endPos.y);
                card.setPosition(new Vector2((float)end,endPos.y));
                i++;
                j=0;
            }

        }





    }

    private void fancyDealing(){

    }



}

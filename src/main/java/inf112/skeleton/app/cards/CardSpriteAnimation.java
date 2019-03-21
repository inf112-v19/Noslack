package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardSpriteAnimation {

    private ArrayList<ProgramCard> hand;
    private int i;
    private int j;
    private Vector2 startPos;
    private Vector2 endPos;

    public CardSpriteAnimation(ArrayList<ProgramCard> hand){

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
            int y = (int)(progress*endPos.y + (1-progress)*startPos.y);
            System.out.println(progress);
            Vector2 pos = new Vector2(x,y);
            ProgramCard card = hand.get(i);
            card.getSprite().setPosition(pos.x,pos.y);
            card.setPosition(pos);
            hand.set(i,card);
            j+=10;

            if(x <= end){
                card.getSprite().setPosition((float)end,endPos.y);
                card.setPosition(endPos);
                i++;
                j=0;
            }

        }





    }

    private void fancyDealing(){

    }



}

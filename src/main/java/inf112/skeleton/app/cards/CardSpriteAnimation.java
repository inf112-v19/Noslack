package inf112.skeleton.app.cards;

import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardSpriteAnimation {

    private ArrayList<ProgramCard> hand;
    private int i;
    private int j;
    private Vector2 startPos;

    public CardSpriteAnimation(ArrayList<ProgramCard> hand){

        this.hand=hand;
        this.startPos = new Vector2(550,400);
        this.i = 0;
        this.j = 0;
        for (ProgramCard card : hand){
            card.getSprite().setPosition(700,555);
        }

    }

    public ArrayList<ProgramCard> updatePositions(){
        shuffleLeft();







        return hand;
    }

    private void shuffleLeft(){
        if(i<hand.size()){
            int end = 25+(i*75);
            Vector2 pos = new Vector2(640-j,555);
            ProgramCard card = hand.get(i);
            card.getSprite().setPosition(pos.x,pos.y);
            card.setPosition(pos);
            hand.set(i,card);
            j+=5;

            if(j == end){
                i++;
                j=0;
            }

        }





    }

    private void fancyDealing(){

    }



}

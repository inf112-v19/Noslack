package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Stack;

public interface IDeck {
    Stack<RRCard> deck = new Stack<>();

    void shuffle();
    void createDeck();
    void reset();
    ArrayList<RRCard> deal(int health);




}

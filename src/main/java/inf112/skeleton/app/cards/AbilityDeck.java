package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

public class AbilityDeck implements IDeck {
    private Stack<RRCard> deck;
    private ArrayList<RRCard> deckList;
    private File file;

    /**
     * Creates a deck of cards
     * @param fileName File needs to be placed in cardDocs directory
     */
    public AbilityDeck(String fileName){

        fileName = "./assets/cardDocs/"+fileName;
        this.file = new File(fileName);
        //file =new File(fileName);
        deck  = new Stack<>();
        deckList = new ArrayList<>();
        createDeck();
    }

    /**
     * Removes all cards in deck and creates a new one
     */
    @Override
    public void reset() {
        deck.clear();
        createDeck();
    }

    /**
     * Creates deck from file, adds them to deckList and shuffles
     */
    @Override
    public void createDeck() {
        try{
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                deckList.add(new AbilityCard(reader.nextLine()));
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        shuffle();
    }

    /**
     * Shuffles deckList and adds them to deck
     */
    @Override
    public void shuffle() {
        Collections.shuffle(deckList);
        deck.addAll(deckList);
        deckList.clear();
    }


    /**
     * Returns cards relative to the remaining health of a player
     * @param health Player health.
     * @return ArrayList of AbilityCards
     */
    @Override
    public ArrayList<RRCard> deal(int health) {
        ArrayList<RRCard> playerDeck = new ArrayList<>();
        playerDeck.add(deck.pop());
        return playerDeck;
    }

    /**
     * Pops one card of the deck and returns it
     * @return RRCard
     */
    @Override
    public RRCard dealOne() {
        return deck.pop();
    }

    /**
     * @param c Card to be returned
     */
    @Override
    public void returnCard(RRCard c) {
        this.deck.insertElementAt(c,(this.deck.size()-1));
    }

    /**
     * Returns size of deck
     * @return Decks size
     */
    @Override
    public int getSize() {
        return this.deck.size();
    }

    /**
     * Returns true if card is in deck
     * @param card the card you are looking for.
     * @return Boolean
     */
    @Override
    public boolean contains(RRCard card) {
        for(RRCard c : deck)
            if(((AbilityCard)c).getAbility().equals(((AbilityCard)card).getAbility()))
                return true;
        return false;
    }

}

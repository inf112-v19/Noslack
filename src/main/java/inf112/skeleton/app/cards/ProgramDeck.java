package inf112.skeleton.app.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class ProgramDeck implements Deck {
    private Scanner reader;
    private ArrayList<RRCard> deckList;
    private Stack<RRCard> deck;
    private File file;
    private int priority;
    private String movement;

    /**
     * Creates the deck of cards
     * @param fileName Name of file that is to be read into the game
     */
    public ProgramDeck(String fileName){
        file = new File(fileName);
        deck  = new Stack<>();
        deckList = new ArrayList<>();
        createDeck();
    }

    @Override
    public void reset() {
        deck.clear();
        createDeck();
    }

    @Override
    public void createDeck() {
        try{
            reader = new Scanner(file);
            while (reader.hasNext()) {
                priority=reader.nextInt();
                reader.skip(" ");
                movement=reader.nextLine();
                deckList.add(new ProgramCard(priority, movement));
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        shuffle();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deckList);
        deck.addAll(deckList);
        deckList.clear();
    }

    @Override
    public ArrayList<RRCard> deal(int health) {
        ArrayList<RRCard> playerDeck = new ArrayList<>();
        for (int i=0;i<health;i++) {
            playerDeck.add(deck.pop());
        }
        return playerDeck;
    }
}

package inf112.skeleton.app.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class ProgramDeck implements IDeck {
    private ArrayList<RRCard> deckList;
    private Stack<RRCard> deck;
    private File file;

    /**
     * Creates the deck of cards
     * @param fileName File needs to be placed in cardDocs directory
     */
    public ProgramDeck(String fileName){
        fileName = ".\\src\\main\\java\\inf112\\skeleton\\app\\cards\\cardDocs\\"+fileName;
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
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                int priority=reader.nextInt();
                reader.skip(" ");
                String movement=reader.nextLine();
                deckList.add(new ProgramCard(priority, movement));
            }
            reader.close();
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

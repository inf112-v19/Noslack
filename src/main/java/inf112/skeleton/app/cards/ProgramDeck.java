package inf112.skeleton.app.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ProgramDeck implements IDeck {
    private ArrayList<RRCard> deckList;
    private Stack<RRCard> deck;
    private File file;

    /**
     * Creates the deck of cards
     * @param fileName File needs to be placed in cardDocs directory
     */
    public ProgramDeck(String fileName){
        fileName = "./assets/cardDocs/"+fileName;
        this.file = new File(fileName);
        this.deck  = new Stack<>();
        this.deckList = new ArrayList<>();
        createDeck();
    }


    @Override
    public void reset() {
        this.deck.clear();
        createDeck();
    }

    @Override
    public void createDeck() {
        try{
            Scanner reader = new Scanner(this.file);
            while (reader.hasNext()) {
                int priority=reader.nextInt();
                reader.skip(" ");
                String movement=reader.nextLine();
                this.deckList.add(new ProgramCard(priority, movement));
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

    /**
     *
     * @param health Player health.
     * @return ArrayList og ProgramCards
     */
    @Override
    public ArrayList<RRCard> deal(int health) {
        ArrayList<RRCard> playerDeck = new ArrayList<>();
        for (int i=0;i<health;i++) {

            playerDeck.add(deck.pop());
        }
        return playerDeck;
    }
}

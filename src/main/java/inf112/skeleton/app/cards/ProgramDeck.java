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
        Collections.shuffle(this.deckList);
        this.deck.addAll(this.deckList);
        this.deckList.clear();
    }

    @Override
    public ArrayList<RRCard> deal(int health) {
        ArrayList<RRCard> playerDeck = new ArrayList<>();
        if(health<5) health=5;
        for (int i=0;i<health;i++)
            if(deck.size()==0) {
                System.out.println("Deck is empty, so playerDeck only contains " + i + " card(s).");
                return playerDeck;
            }
            else
                playerDeck.add(this.deck.pop());
        return playerDeck;
    }

    @Override
    public RRCard dealOne() {
        return this.deck.pop();
    }

    @Override
    public void returnCard(RRCard c) {
        this.deck.insertElementAt(c,(this.deck.size()-1));
    }

    @Override
    public int getSize() {
        return this.deck.size();
    }

    @Override
    public boolean contains(RRCard card) {
        for (RRCard c :deck)
            if(c.compareTo(card)==0)
                return true;
        return false;
    }
}

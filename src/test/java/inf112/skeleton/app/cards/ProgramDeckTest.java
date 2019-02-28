package inf112.skeleton.app.cards;

import org.junit.Test;
import org.lwjgl.Sys;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ProgramDeckTest {
    private String filename = "testProgram.txt";
    private String filename2 = "testProgram2.txt";

    @Test
    public void sizeTest() {
        IDeck deck = new ProgramDeck(this.filename);
        assertEquals(1,deck.getSize());
    }

    @Test
    public void getInformationOnElement() {
        IDeck deck = new ProgramDeck(this.filename);
        ArrayList<RRCard> testDeck;
        //ProgramCard card= new ProgramCard(820,"Move 3");
        ProgramCard card;

        int priority=820;
        Program program = Program.MOVE3;

        testDeck=deck.deal(1);
        card=(ProgramCard)testDeck.get(0);

        assertEquals(priority,card.getPriority());
        assertSame(program,card.getMove());


    }

    @Test
    public void reset() {
        IDeck deck = new ProgramDeck(this.filename2);
        int size =17;
        deck.deal(5);
        assertNotEquals(size,deck.getSize());
        deck.reset();
        assertEquals(size,deck.getSize());
    }

    @Test
    public void dealOne() {
        IDeck deck = new ProgramDeck(this.filename2);
        ArrayList<RRCard> testDeck= new ArrayList<>();
        int size = deck.getSize();

        testDeck.add(deck.dealOne());
        assertEquals(1,testDeck.size());
        assertEquals((size-1),deck.getSize());

    }

    @Test
    public void contains() {
        IDeck deck = new ProgramDeck(this.filename2);
        RRCard card = deck.dealOne();
        assertFalse(deck.contains(card));
        deck.reset();
        assertTrue(deck.contains(card));
    }

    @Test
    public void returnCard() {
        IDeck deck = new ProgramDeck(this.filename2);
        RRCard card = deck.dealOne();
        deck.returnCard(card);
        assertTrue(deck.contains(card));
    }
}
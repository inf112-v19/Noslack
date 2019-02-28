package inf112.skeleton.app.cards;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AbilityDeckTest {
    private String filename = "testAbility.txt";
    private String filename2 = "testAbility2.txt";

    @Test
    public void sizeTest() {
        IDeck deck = new AbilityDeck(this.filename);
        assertEquals(1,deck.getSize());
    }

    @Test
    public void getAbilityTest() {
        IDeck deck = new AbilityDeck(this.filename);
        ArrayList<RRCard> testDeck;
        testDeck=deck.deal(0);
        AbilityCard card = (AbilityCard)testDeck.get(0);
        assertSame(Ability.DoubleBarreledLaser,card.getAbility());
    }

    @Test
    public void reset() {
        IDeck deck = new AbilityDeck(this.filename2);
        int size = deck.getSize();
        for (int i =0;i<6;i++)
            deck.dealOne();
        assertNotEquals(size,deck.getSize());
        deck.reset();
        assertEquals(size,deck.getSize());
    }

    @Test
    public void dealOne() {
        IDeck deck = new AbilityDeck(this.filename2);
        ArrayList<RRCard> testDeck = new ArrayList<>();
        int size = deck.getSize();

        testDeck.add(deck.dealOne());
        assertEquals(1,testDeck.size());
        assertEquals((size-1),deck.getSize());
    }

    @Test
    public void contains() {
        IDeck deck = new AbilityDeck(this.filename2);
        RRCard card = deck.dealOne();
        assertFalse(deck.contains(card));
        deck.reset();
        assertTrue(deck.contains(card));
    }
}
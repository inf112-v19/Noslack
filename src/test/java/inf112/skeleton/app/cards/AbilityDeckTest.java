package inf112.skeleton.app.cards;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AbilityDeckTest {
    private String filename = "testAbility.txt";

    @Test
    public void sizeTest() {
        Deck deck = new AbilityDeck(filename);
        ArrayList<RRCard> testDeck;

        testDeck=deck.deal(0);
        assertEquals(1,testDeck.size());
    }

    @Test
    public void getAbilityTest() {
        Deck deck = new AbilityDeck(filename);
        ArrayList<RRCard> testDeck;
        testDeck=deck.deal(0);
        AbilityCard card = (AbilityCard)testDeck.get(0);
        assertSame(Ability.DoubleBarreledLaser,card.getAbility());
    }
}
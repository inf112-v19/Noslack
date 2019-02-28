package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public interface IDeck {
    /**
     * Creates a new deck of cards.
     */
    void createDeck();

    /**
     * Shuffles a list of elements
     */
    void shuffle();

    /**
     * Resets the deck
     */
    void reset();

    /**
     * Deals cards to player based on the players health
     * @param health Player health.
     * @return A small deck of cards.
     */
    ArrayList<RRCard> deal(int health);

    /**
     * Deal a single card to player
     * @return An RRCard
     */
    RRCard dealOne();

    /**
     * Find size of deck
     * @return the size of the deck
     */
    int getSize();

    /**
     * Find out if the deck contains a Card.
     * @param card the card you are looking for.
     * @return If the card is there.
     */
    boolean contains(RRCard card);



}

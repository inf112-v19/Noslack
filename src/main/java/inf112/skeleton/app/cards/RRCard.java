package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
public interface RRCard  extends Comparable {

    /**
     * Finds if the cards are the same,
     * @param card  The card to be compared.
     * @return If the cards are equal
     */
    boolean equals(RRCard card);
    /**
     * This function returns the Cards Sprite.
     * @return the cards Sprite
     */
    Sprite getSprite();

    /**
     * Returns a description of the card.
     * For the Program cards it returns a description of the move.
     * For the Ability card it returns a comprehensive description of the ability
     * @return A description of the card
     */
    String toString();

    /**
     * This function returns the cards position.
     * @return Position Of card
     */
    Vector2 getPosition();




}


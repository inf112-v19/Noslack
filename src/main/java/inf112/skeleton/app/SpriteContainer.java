package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.cards.AbilityCard;
import inf112.skeleton.app.cards.ProgramCard;

import java.util.ArrayList;

public class SpriteContainer {

    private Sprite dealtCardsBackgroundSprite;
    private Sprite selectedCardsBackgroundSprite;
    private Sprite cardTestSprite;
    private Sprite currentSprite;
    private Sprite goButton;
    private SpriteBatch batch;
    private int drawPositionX;
    private int drawPositionY;
    private final int TILE_SIZE = 32;
    private AbilityCard currentAbility;
    private AbilityCard emptyAbility;





    public SpriteContainer(SpriteBatch batch){

        this.batch = batch;
        this.dealtCardsBackgroundSprite = setSprite("./assets/cards/dealtCardsBackground.png");
        this.selectedCardsBackgroundSprite = setSprite("./assets/cards/KortBakgrunn2.png");
        this.cardTestSprite = setSprite("./assets/cards/back-up.png");
        this.goButton = setSprite("./assets/cards/dontpress.png");
        this.goButton.setPosition(33, 220);
        this.emptyAbility = new AbilityCard(" ");
        this.currentAbility = emptyAbility;





    }

    private void renderDealtCards() {
        this.drawPositionX = 0;
        this.drawPositionY = 40 + TILE_SIZE * 4;

        // Draw background for dealt cards.

        this.dealtCardsBackgroundSprite.setPosition(this.drawPositionX, this.drawPositionY);
        this.dealtCardsBackgroundSprite.draw(this.batch);

        this.selectedCardsBackgroundSprite.draw(this.batch);



        this.currentAbility.getSprite().draw(this.batch);






        //cardTestSprite.draw(batch);
    }

    private void renderProgramCards(ArrayList<ProgramCard> programHand){

        for (ProgramCard card : programHand) {
            card.getSprite().draw(this.batch);
        }
    }

    private Sprite setSprite(String texturePath) {
        Texture texture = new Texture(Gdx.files.internal(texturePath));
        return new Sprite(texture);
    }
}

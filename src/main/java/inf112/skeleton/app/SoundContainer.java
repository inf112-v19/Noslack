package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundContainer {

    private Sound gameMusic;
    private Sound shuffleCard;


    public SoundContainer(){

        this.gameMusic = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/gameTheme.wav"));
        this.gameMusic.loop();
        this.shuffleCard = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/shuffleCard.wav"));


    }

    public void laserSound(){

    }

    public void conveyorSound(){

    }

    public void defeatSound(){

    }

    public void gameMusic(){

    }

    public void rotateSound(){

    }

    public void shuffleCardSound(){
        shuffleCard.play();

    }

    public void victorySound(){

    }

    public void takeDamageSound(){

    }

}

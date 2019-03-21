package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundContainer {

    private Sound gameMusic;


    public SoundContainer(){

        this.gameMusic = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/gameTheme.wav"));
        this.gameMusic.loop();


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

    }

    public void victorySound(){

    }

    public void takeDamageSound(){

    }
    
}

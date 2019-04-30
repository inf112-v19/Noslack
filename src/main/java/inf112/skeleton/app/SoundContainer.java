package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundContainer {

    private Sound gameMusic;
    private Sound shuffleCard;
    private Sound conveyor;
    private Sound laser;
    private Sound move;
    private boolean playing;



    public SoundContainer(){

        this.gameMusic = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/gameTheme.wav"));
        this.shuffleCard = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/shuffleCard.wav"));
        this.conveyor = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/conveyor.wav"));
        this.move = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/move.wav"));
        this.laser = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/laser.wav"));

        this.playing = false;

    }

    public void laserSound(){
        this.laser.play();

    }

    public void conveyorSound()
    {

    }
    public void move(){
        this.move.play();
    }

    public void defeatSound(){

    }

    public void gameMusic(){
        this.gameMusic.loop();
        this.playing = true;
    }

    public void pauseGameMusic(){
        this.gameMusic.pause();
        this.playing = false;
    }

    public void resumeGameMusic(){
        this.gameMusic.resume();
        this.playing = true;
        //this.gameMusic.setPitch();
    }

    public boolean isGameMusicPlaying(){
        return playing;
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

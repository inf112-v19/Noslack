package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundContainer {

    private Sound gameMusic;
    private Sound shuffleCard;
    private Sound conveyor;
    private Sound laser;
    private Sound teleport;
    private Sound move;
    private Sound victory;
    private Sound defeat;
    private boolean playing;



    public SoundContainer(){


        try {
            this.gameMusic = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/gameTheme.wav"));
            this.shuffleCard = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/shuffleCard.wav"));
            this.conveyor = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/conveyor.wav"));
            this.move = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/move.wav"));
            this.laser = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/laser.wav"));
            this.teleport = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/portal.wav"));
            this.victory = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/victoryroyale.wav"));
            this.defeat = Gdx.audio.newSound(Gdx.files.internal("./assets/sound/rip.wav"));

        }catch(Exception e){
        }

        this.playing = false;

    }

    public void laserSound(){
        try {
            this.laser.play();
        }catch(Exception e){
        }
    }
    public void teleportSound(){
        try{
            this.teleport.play(0.2f);
        }catch(Exception e){

        }
    }

    public void conveyorSound() {
        try{
            this.conveyor.play();
        }catch(Exception e){
        }

    }
    public void move(){
        try {
            this.move.play();
        }catch(Exception e){
        }
    }

    public void defeatSound(){
        try {
            this.defeat.play();
        }catch(Exception e){
        }
    }

    public void gameMusic(){
        try {
            this.gameMusic.loop();
            this.playing = true;
        }catch(Exception e){
        }

    }

    public void pauseGameMusic(){
        try {
            this.gameMusic.pause();
            this.playing = false;
        }catch(Exception e){
        }

    }

    public void stopMusic(){
        try {
            this.gameMusic.stop();
        }catch(Exception e){
        }

    }

    public void resumeGameMusic(){
        try {
            this.gameMusic.resume();
            this.playing = true;
        }catch(Exception e){
        }

    }

    public boolean isGameMusicPlaying(){
        return playing;
    }

    public void shuffleCardSound(){

        try {
            this.shuffleCard.play();
        }catch(Exception e){
        }

    }

    public void victorySound(){
        try {
            victory.play();
        }catch(Exception e){
        }
    }
}

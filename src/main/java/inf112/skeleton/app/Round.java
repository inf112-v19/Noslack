
package inf112.skeleton.app;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Player;

import java.util.ArrayList;

public class Round {

    private Player[] players;
    public ProgramDeck ProgramCards;
    public AbilityDeck AbilityCards;
    private TileGrid tileGrid;


    private final int PHASES = 5;

    /**
     * Need to decide if list of players or player nr
     * @param players List of players that are in the game.
     */
    public Round(Player[] players, TileGrid tileGrid){
        this.tileGrid = tileGrid;
        this.players = players;
        createDecks();
    }

    public void dealCards(){
        for (Player player:players)
            player.drawCards(ProgramCards.deal(player.getHealth()), AbilityCards.deal(player.getHealth()));
    }



    /**
     * Run through the phases.
     * TODO
     */
    public void run(){
        // Go through logic for each phase
        for(int i =0;i<PHASES;i++){
            // Go through logic for each player
            /* Todo:
            * Implement priority functionality for multiple players.
            */
            for(Player player : players){
                ProgramCard nextProgram = player.getNextProgram();
                Program move = nextProgram.getMove();
                int priority = nextProgram.getPriority();
                //tileGrid.applyNextProgram(0);
            }

        }
        reset();
    }

    private void createDecks(){
        ProgramCards = new ProgramDeck("ProgramCards.txt");
        AbilityCards = new AbilityDeck("AbilityCards.txt");
    }


    public void render(){

    }
    /**
     * Reset for new round
     */
    protected void reset(){
        ProgramCards.reset();
        AbilityCards.reset();
        //for (Player player:Players) player.reset();
    }

}

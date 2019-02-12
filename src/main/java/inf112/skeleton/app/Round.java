package inf112.skeleton.app;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Player;

import java.util.ArrayList;

public class Round {
    private ArrayList<Player> Players;
    public ProgramDeck ProgramCards;
    public AbilityDeck AbilityCards;


    private final int FASES = 5;

    /**
     * Need to decide if list of players or player nr
     * @param Players List of players that are in the game.
     */
    public Round(ArrayList<Player> Players){
        this.Players = Players;
        createDecks();
    }

    public void dealCards(){
        for (Player player:Players)
            player.drawCards(ProgramCards.deal(player.getHealth()), AbilityCards.deal(player.getHealth()));
    }



    /**
     * Run through the fases.
     * TODO
     */
    public void run(){
        for(int i =0;i<FASES;i++){


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
        for (Player player:Players) player.reset();
    }
}

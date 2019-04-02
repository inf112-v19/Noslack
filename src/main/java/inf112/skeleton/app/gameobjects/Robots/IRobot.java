package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.AbilityCard;
import inf112.skeleton.app.cards.Program;
import inf112.skeleton.app.cards.ProgramCard;
import inf112.skeleton.app.cards.RRCard;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;
import java.util.Stack;

public interface IRobot extends GameObject {

    /**
     * Get the players number
     * @return The players number.
     */
    int getPlayerNumber();

    /**
     * Get method for health
     * @return Players health
     */
    int getHealth();

    /**
     * Removes one health from the player.
     */
    void receiveDamage();

    /**
     * Remove given amount of health from player
     * @param damage amount of health to be deducted
     */
    void receiveDamage(int damage);

    /**
     * @return How many lives the player has left.
     */
    int getLives();

    /**
     * Give player a new orientation
     * @param orientation The players new orientation
     */
    void setOrientation(Orientation orientation);

    /**
     * Method for updating orientation
     * @param rotation ??
     */
    void updateOrientation(Program rotation);

    /**
     * Set a new psoition for the player
     * @param position New position
     */
    void setPosition(Coordinate position);

    /**
     * Get the players position
     * @return Players current position
     */
    Coordinate getPosition();

    /**
     * Give Cards to Player
     * @param AbilityCards IDeck of AbilityCards
     * @param ProgramCards IDeck of ProgramCards
     */
    void drawCards(ArrayList<RRCard> ProgramCards, ArrayList<RRCard> AbilityCards);
    /**
     * @return Player AbilityDeck
     */
    ArrayList<AbilityCard> getAbilityHand();

    /**
     * @return Players ProgramDeck
     */
    ArrayList<ProgramCard> getProgramHand();
    /**
     * @return Program for round
     */
    Stack<ProgramCard> getProgram();

    /**
     * Set the players next program from the program stack.
     */
    void setNextProgram();

    /**
     * Set the next program to be executed
     * @param currentMove The current Program to be executed
     */
    void setCurrentMove(Program currentMove);

    /**
     * Get the players current program.
     * @return The current program.
     */
    Program getCurrentMove();

    /**
     * Replenishes the players health by 1, up to a maximum of 9 (no damage).
     */
    void repair();

    /**
     * Set the size of the flags visited array, based on how many flags the map contains.
     * @param n The number of flags on the map.
     */
    void setFlagsVisitedSize(int n);

    /**
     * @return Get the list of which flags the player has visited.
     */
    boolean[] getFlagsVisited();

    /**
     * Initiate a visit to a flag.
     * @param n The flags number.
     */
    void visitFlag(int n);

    /**
     * Checks if flag has been visited.
     * @param n Flag number
     * @return If the flag has been visited.
     */
    boolean getFlag(int n);

    void initiate(Coordinate cor);
    /**
     * Get the move progression for the players current program
     * @return How far the player has gotten in the move.
     */
    int getMoveProgression();
    /**
     * Progress the players current move.
     */
    void progressMove();

    /**
     * Stop the current program.
     */
    void resetMoveProgress();

    /**
     * Reset player for new round
     */
    void reset();

    /**
     * Respawn player after death
     */
    void respawn();

    /**
     * Set the players Back Up
     */
    void setBackUp();

    /**
     * Get the players Back Up
     * @return the players Back Up
     */
    Coordinate getBackUp();

    /**
     * @return If program chosen by player is completed
     */
    boolean isFinished();

    /**
     * Call if player wins
     */
    void win();
}
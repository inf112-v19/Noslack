package inf112.skeleton.app.gameobjects.Robots;

import inf112.skeleton.app.cards.*;
import inf112.skeleton.app.gameobjects.Coordinate;
import inf112.skeleton.app.gameobjects.GameObject;
import inf112.skeleton.app.gameobjects.Orientation;

import java.util.ArrayList;
import java.util.Stack;

public interface IRobot extends GameObject {

    /**
     * Get the robots number
     * @return The robots number.
     */
    int getRobotNumber();

    /**
     * Get method for health
     * @return Robots health
     */
    int getHealth();

    /**
     * Get method for hasMoved
     * Used in ActivateTiles so we don't move robot twice
     * @return hasMoved
     */
    boolean hasMoved();

    /**
     * Void setter-method for moved
     * Sets hasMoved to true/false
     */
    void moved(boolean bol);



    /**
     * Removes one health from the robot.
     */
    boolean receiveDamage();

    /**
     * Remove given amount of health from robot
     * @param damage amount of health to be deducted
     */
    boolean receiveDamage(int damage);

    /**
     * @return How many lives the robot has left.
     */
    int getLives();

    /**
     * Give robot a new orientation
     * @param orientation The robots new orientation
     */
    void setOrientation(Orientation orientation);

    /**
     * Method for updating orientation
     * @param rotation ??
     */
    void updateOrientation(Program rotation);

    /**
     * Set a new psoition for the robot
     * @param position New position
     */
    void setPosition(Coordinate position);

    /**
     * Get the robots position
     * @return Robots current position
     */
    Coordinate getPosition();

    /**
     * Give Cards to Robot
     * @param programCards IDeck of ProgramCards
     */
    void drawPrograms(ArrayList<RRCard> programCards);

    /**
     * Draw an ability card for robot
     * @param abilityCard Ability card
     */
    void drawAbility(RRCard abilityCard);

    /**
     * Discards an Ability from the robot
     * @param card The ability card to be discarded
     */
    void discardAbility(RRCard card);

    /**
     * Receive extra card from deck.
     * @param card RRcard
     */
    void extraCard(RRCard card);

    /**
     * @return Robots AbilityDeck
     */
    ArrayList<AbilityCard> getAbilityHand();

    /**
     * @return Robots ProgramDeck
     */
    ArrayList<ProgramCard> getProgramHand();
    /**
     * @return Program for round
     */
    Stack<ProgramCard> getProgram();

    /**
     * Get the Priority of the next ProgramCard
     * @return the Priority
     */
    int getNextProgramPriority();

    /**
     * Set the robots next program from the program stack.
     */
    void setNextProgram();

    /**
     * Set the next program to be executed
     * @param currentMove The current Program to be executed
     */
    void setCurrentMove(Program currentMove);

    /**
     * Get the robots current program.
     * @return The current program.
     */
    Program getCurrentMove();

    /**
     * Replenishes the robots health by 1, up to a maximum of 9 (no damage).
     */
    void repair();

    /**
     * Set the size of the flags visited array, based on how many flags the map contains.
     * @param n The number of flags on the map.
     */
    void setFlagsVisitedSize(int n);

    /**
     * @return Get the list of which flags the robot has visited.
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
     * Push the robots chosen program into a queue
     * @param selectedCards The selected programs
     */
    void pushProgram(ArrayList<ProgramCard> selectedCards);

    /**
     * Get the move progression for the robots current program
     * @return How far the robot has gotten in the move.
     */
    int getMoveProgression();
    /**
     * Progress the robots current move.
     */
    void progressMove();

    /**
     * Get the robots ability, which is decided from the robots ability card.
     * @return The robots ability
     */
    boolean hasAbility(Ability ability);

    /**
     * Activate the robots ability
     */
    void activateAbility();

    boolean getActiveAbility();

    /**
     * Stop the current program.
     */
    void resetMoveProgress();

    /**
     * Reset robot for new round
     */
    void reset();

    /**
     * Respawn robot after death
     */
    void respawn();

    /**
     * Set the robots Back Up
     */
    void setBackUp();

    /**
     * Get the robots Back Up
     * @return the robots Back Up
     */
    Coordinate getBackUp();

    /**
     * Powers down or powers up robot
     */
    void powerDown();

    /**
     * Finds out if the Robot is powered down
     * @return If the robot is powered down
     */
    boolean isPoweredDown();

    /**
     * @return If program chosen by robot is completed
     */
    boolean isFinished();

    /**
     * Call if robot wins
     */
    void win();

    /**
     * Finds out if the Robot is an AI
     * @return True if the Robot is an AI
     */
    boolean isAI();
}
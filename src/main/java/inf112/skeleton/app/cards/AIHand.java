package inf112.skeleton.app.cards;

import java.util.ArrayList;

public class AIHand {
    private ArrayList<ProgramCard> hand;

    public AIHand(ArrayList<RRCard> hand){
        this.hand = new ArrayList<>();
        for (RRCard card : hand){
            this.hand.add((ProgramCard)card);
        }
    }

    /**
     * Add extra cerd to hand
     * @param card
     */
    public void addExtraCard(RRCard card){
        this.hand.add((ProgramCard)card);
    }

    /**
     * Finds out if the hand contains a Program
     * @param program The wanted program
     * @return If the hand contains the wanted program.
     */
    public boolean contains(Program program){
        for(ProgramCard card : this.hand){
            if(card.getMove().equals(program))
                return true;
        }
        return false;
    }

    /**
     * Finds out if the hand contains a number of certain Programs
     * @param program The wanted program
     * @param n The number wanted
     * @return If the program contains the wanted programs
     */
    public boolean contains(Program program, int n){
        int i = 0;
        for(ProgramCard card : this.hand){
            if(card.getMove().equals(program))
                i++;
        }
        return (n<i);
    }

    /**
     * Get a Card based on a program
     * @param program The wanted program
     * @return The resulting card
     */
    public ProgramCard get(Program program){
        for(int i = 0; i<hand.size(); i++){
            if(hand.get(i).getMove().equals(program)){
                return hand.remove(i);
            }
        }
        return null;
    }

    /**
     * Get a card from the hand and remove from use.
     * @param card The wanted card
     * @return The card
     */
    public ProgramCard get(ProgramCard card){
        int i = hand.indexOf(card);
        return hand.remove(i);
    }

    public boolean containsMove() {
        System.out.println(hand.size());
        for(ProgramCard card: hand){
            return card.getMove().isMove();
        }
        for(ProgramCard card: hand){
            return card.getMove().isMove();
        }
        return false;
    }

    /**
     * Finds a forward or backward move inside the hand.
     * @return A movement program.
     */
    public ProgramCard findMove() {
        for(ProgramCard card: hand){
            if(card.getMove().isMove() && !card.getMove().equals(Program.BACK)){
               return get(card);
            }
        }
        if(contains(Program.BACK)) {
            return get(Program.BACK);
        }
        return null;
    }

    public boolean containsTurn() {
        for(ProgramCard card: hand){
            if(!card.getMove().isMove()){
                return true;
            }
        }
        return false;
    }

    public ProgramCard findTurn(){
        for (ProgramCard card : hand) {
            if(!card.getMove().isMove()){
                return get(card);
            }
        }
        return null;
    }

    public ArrayList<ProgramCard> getHand(){
        return this.hand;
    }

    public ProgramCard getFirst() {
        return this.hand.get(0);
    }
}

package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Abillty cards for the game RoboRally.
 * Uses the interface RRCard
 */
public class AbilityCard implements RRCard {
    private Ability ability;
    private Sprite sprite;
    private String name;
    private Texture texture;
    private Vector2 position;


    /**
     * Create ability card
     * @param ability Ability to be assigned to the card
     */
    public AbilityCard (String ability){
        this.ability=setAbility(this.name=ability);
    }

    private Ability setAbility(String s){
        switch (s) {
            case "Ablative Coat":
                setSprite("./assets/abilityCards/AblativeCoat.png");
                return Ability.AblativeCoat;
            case "Abort Switch":
                setSprite("./assets/abilityCards/AbortSwitch.png");
                return Ability.AbortSwitch;
            case "Brakes":
                setSprite("./assets/abilityCards/BacksideOfCards.png");
                return Ability.Brakes;
            case "Circuit Breaker":
                setSprite("./assets/abilityCards/CircuitBreaker.png");
                return Ability.CircuitBreaker;
            case "Conditional Program":
                setSprite("./assets/abilityCards/ConditionalProgram.png");
                return Ability.ConditionalProgram;
            case "Crab Legs":
                setSprite("./assets/abilityCards/CrabLegs.png");
                return Ability.CrabLegs;
            case "Double-Barreled Laser":
                setSprite("./assets/abilityCards/DoubleBarreledLaser.png");
                return Ability.DoubleBarreledLaser;
            case "Dual Processor":
                setSprite("./assets/abilityCards/DualProcessor.png");
                return Ability.DualProcessor;
            case "Extra Memory":
                setSprite("./assets/abilityCards/ExtraMemory.png");
                return Ability.ExtraMemory;
            case "Fire Control":
                setSprite("./assets/abilityCards/FireControl.png");
                return Ability.FireControl;
            case "Flywheel":
                setSprite("./assets/abilityCards/Flywheel.png");
                return Ability.Flywheel;
            case "Fourth Gear":
                setSprite("./assets/abilityCards/FourthGear.png");
                return Ability.FourthGear;
            case "Gyroscopic Stabilizer":
                setSprite("./assets/abilityCards/GyroscopicStabilizer.png");
                return Ability.GyroscopicStabilizer;
            case "High-Powered Laser":
                setSprite("./assets/abilityCards/HighPowerLaser.png");
                return Ability.HighPoweredLaser;
            case "Mechanical Arm":
                setSprite("./assets/abilityCards/MechanicalArm.png");
                return Ability.MechanicalArm;
            case "Mini Howitzer":
                setSprite("./assets/abilityCards/MiniHowitzer.png");
                return Ability.MiniHowitzer;
            case "Power-Down Shield":
                setSprite("./assets/abilityCards/PowerDownShield.png");
                return Ability.PowerDownShield;
            case "Pressor Beam":
                setSprite("./assets/abilityCards/PressorBeam.png");
                return Ability.PressorBeam;
            case "Radio Control":
                setSprite("./assets/abilityCards/RadioControl.png");
                return Ability.RadioControl;
            case "Ramming Gear":
                setSprite("./assets/abilityCards/RammingGear.png");
                return Ability.RammingGear;
            case "Rear-Firing Laser":
                setSprite("./assets/abilityCards/RearFiringLaser.png");
                return Ability.RearFiringLaser;
            case "Recompile":
                setSprite("./assets/abilityCards/Recompile.png");
                return Ability.Recompile;
            case "Reverse Gear":
                setSprite("./assets/abilityCards/ReverseGear.png");
                return Ability.ReverseGear;
            case "Scrambler":
                setSprite("./assets/abilityCards/Scrambler.png");
                return Ability.Scrambler;
            case "Superior Archive":
                setSprite("./assets/abilityCards/SuperiorArchive.png");
                return Ability.SuperiorArchive;
            case "Tractor Beam":
                setSprite("./assets/abilityCards/TractorBeam.png");
                return Ability.TractorBeam;
            default:
                throw new IllegalArgumentException("Invalid ability: " + s);

        }
    }


    private void setSprite(String filepath){
        try {
            Texture cardTexture = new Texture(Gdx.files.internal(filepath));
            this.sprite = new Sprite(cardTexture);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * @return The ability of the card, as an Ability enum.
     */
    public Ability getAbility() {
        return this.ability;
    }


    private void setSprite(String filepath){
        try {
            this.texture = new Texture(Gdx.files.internal(filepath));
            this.sprite = new Sprite(this.texture);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        if(getAbility().equals(((AbilityCard)o).getAbility()))
            return 0;
        else
            return -1;
    }

    @Override
    public String toString(){
        return getAbility().toString();
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }
}

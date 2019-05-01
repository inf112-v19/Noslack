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
    private Vector2 position;


    /**
     * Create ability card
     * @param ability Ability to be assigned to the card
     */
    public AbilityCard (String ability){
        this.ability=setAbility(ability);
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
                setSprite("./assets/abilityCards/Brakes.png");
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
            case " ":
                setSprite("./assets/abilityCards/default.png");
                return Ability.Empty;
            default:
                throw new IllegalArgumentException("Invalid ability: " + s);

        }
    }

    private void setSprite(String filepath){
        try {
            this.sprite = new Sprite(new Texture(Gdx.files.internal(filepath)));
        }
        catch (Exception e){
        }
    }


    /**
     * @return The ability of the card, as an Ability enum.
     */
    public Ability getAbility() {
        return this.ability;
    }

    /**
     * Finds out if the ability needs to be active to work
     * @return If it needs to be activated to function.
     */
    public boolean activeAbility(){
        return this.ability.activeAbility();
    }


    @Override
    public boolean equals(RRCard card) {
        return this.ability.equals(((AbilityCard)card).getAbility());
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public int compareTo(Object o) {
        if(getAbility().equals(((AbilityCard)o).getAbility()))
            return 0;
        else
            return -1;
    }

    @Override
    public String toString(){
        return this.ability.toString();
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }
}

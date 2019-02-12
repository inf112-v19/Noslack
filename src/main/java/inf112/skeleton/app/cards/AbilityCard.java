package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Abillty cards for the game RoboRally.
 * Uses the interface RRCard
 */
public class AbilityCard implements RRCard {
    private Ability ability;
    private Texture texture;
    private Sprite sprite;


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
                return Ability.AblativeCoat;
            case "Abort Switch":
                return Ability.AbortSwitch;
            case "Brakes":
                return Ability.Brakes;
            case "Circuit Breaker":
                return Ability.CircuitBreaker;
            case "Conditional Program":
                return Ability.ConditionalProgram;
            case "Crab Legs":
                return Ability.CrabLegs;
            case "Double-Barreled Laser":
                return Ability.DoubleBarreledLaser;
            case "Dual Processor":
                return Ability.DualProcessor;
            case "Extra Memory":
                return Ability.ExtraMemory;
            case "Fire Control":
                return Ability.FireControl;
            case "Flywheel":
                return Ability.Flywheel;
            case "Fourth Gear":
                return Ability.FourthGear;
            case "Gyroscopic Stabilizer":
                return Ability.GyroscopicStabilizer;
            case "High-Powered Laser":
                return Ability.HighPoweredLaser;
            case "Mechanical Arm":
                return Ability.MechanicalArm;
            case "Mini Howitzer":
                return Ability.MiniHowitzer;
            case "Power-Down Shield":
                return Ability.PowerDownShield;
            case "Pressor Beam":
                return Ability.PressorBeam;
            case "Radio Control":
                return Ability.RadioControl;
            case "Ramming Gear":
                return Ability.RammingGear;
            case "Rear-Firing Laser":
                return Ability.RearFiringLaser;
            case "Recompile":
                return Ability.Recompile;
            case "Reverse Gear":
                return Ability.ReverseGear;
            case "Scrambler":
                return Ability.Scrambler;
            case "Superior Archive":
                return Ability.SuperiorArchive;
            case "Tractor Beam":
                return Ability.TractorBeam;
            default:
                throw new IllegalArgumentException("Invalid ability: " + s);

        }
    }

    /**
     * @return The ability of the card, as an Ability enum.
     */
    public Ability getAbility() {
        return ability;
    }

    @Override
    public void evaluateSprite() {
        /*
         * Todo:
         * Add cases for each Program enum to evaluate
         * into each texture.
         */
        switch(this.ability){
            case AblativeCoat:

                break;
            case AbortSwitch:

                break;
            case Brakes:

                break;
            case CrabLegs:

                break;
            case CircuitBreaker:

                break;
            case ConditionalProgram:

                break;
            case DoubleBarreledLaser:

                break;
            case DualProcessor:

                break;
            case ExtraMemory:

                break;
            case FireControl:

                break;
            case Flywheel:

                break;
            case FourthGear:

                break;
            case GyroscopicStabilizer:

                break;
            case HighPoweredLaser:

                break;
            case MechanicalArm:

                break;
            case MiniHowitzer:

                break;
            case PowerDownShield:

                break;
            case PressorBeam:

                break;
            case RadioControl:

                break;
            case RammingGear:

                break;
            case Recompile:

                break;
            case RearFiringLaser:

                break;
            case ReverseGear:

                break;
            case Scrambler:

                break;
            case SuperiorArchive:

                break;
            case TractorBeam:

                break;
            default: this.texture = new Texture(Gdx.files.internal("./assets/error.png"));
        }

        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}

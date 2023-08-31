/**
 * An abstract data type meant to serve as a representation of a cargo
 * containing its name, weight and CargoStrength. This data type is immutable
 * therefore changes cannot be made to these values once initialized.
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class Cargo 
{
    private String name; //Name of the Cargo
    private double weight;//Weight of the Cargo
    private CargoStrength strength;//Strength the Cargo has

    /**
     * Default Constructor.
     * @param initName
     *      Non-null name for the cargo item.
     * @param initWeight
     *      The weight for the cargo. The weight should be greater than 0, so an exception should be thrown if initWeight ≤ 0.
     * @param initStrength
     *      Either FRAGILE, MODERATE, or STURDY.
     * @custom.Precondition
     *      initName is not null.
     *      initWeight > 0.
     * @custom.Postcondition
     *      This object has been initialized to a Cargo object with the given name, weight, and strength.
     * @throws IllegalArgumentException
     *      If initName is null.
     *      If initWeight ≤ 0.
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength) throws IllegalArgumentException
    {
        //Precondition check 1
        if(initName==null)
        {
            throw new IllegalArgumentException("The name of the cargo cannot be null.");
        }

        //Precondition check 2
        if(initWeight <= 0)
        {
            throw new IllegalArgumentException("The cargo cannot be weightless.");
        }

        this.name = initName;
        this.weight = initWeight;
        this.strength = initStrength;
    }

    /**
     * Returns the name of the cargo
     * @return
     *      name of the cargo
     */
    public String getName() 
    {
        return this.name;
    }

    /**
     * returns the weight of the cargo
     * @return
     *      weight of the cargo
     */
    public double getWeight() 
    {
        return this.weight;
    }

    /**
     * returns the strength of the cargo
     * @return
     *      strength of the cargo
     */
    public CargoStrength getStrength() 
    {
        return this.strength;
    }

    /**
     * Creates a clone of the Cargo
     * @return
     *      the new cargo
     */
    public Cargo clone()
    {
        return new Cargo(this.name, this.weight, this.strength);
    }
}

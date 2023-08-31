/**
 * An Enum class which stores the strength the Cargo has. 
 * The enum constants include Fragile, Moderate and Sturdy Strength
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public enum CargoStrength 
{
	FRAGILE(1),
	MODERATE(2),
	STURDY(3);

	private int strengthValue;

	/**
	 * Default Constructor
	 * @param initStrengthValue
	 * 		value of strength according to how they can be placed
	 */
	CargoStrength(int initStrengthValue)
	{
		this.strengthValue = initStrengthValue;
	}

	/**
	 * Returns the Strength Value of Enum
	 * @return
	 * 		the strength value
	 */
	public int getStrengthValue()
	{
		return this.strengthValue;
	}
}

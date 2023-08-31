/**
 * An exception thrown in the <CODE>CargoShip</CODE> class to indicate that 
 * the new cargo cannot be pushed onto stack because a weaker cargo is below it
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class CargoStrengthException extends Exception
{
    /**
     * Default constructor for an CargoStrengthException that
     * passes a default string to the Exception class constructor.
     *
     * @custom.Postcondition:
     *    The object is created and contains the default message.
     */
    public CargoStrengthException()
    {
        //Default Message
        super("Operation failed! Cargo at top of stack cannot support weight.");
    }

    /**
     * Second constructor for the CargoStrengthException that
     * passes a provided string to the Exception class constructor.
     *
     * @param errorMessage
     *    the message that the object is to contain
     *    
     * @custom.Postcondition:
     *    The object is created and contains the provided message.
     */
    public CargoStrengthException(String errorMessage)
    {
        //Passed Message
        super(errorMessage);
    }
}
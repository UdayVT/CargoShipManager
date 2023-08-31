/**
 * An exception thrown in the <CODE>CargoShip</CODE> class to indicate that 
 * after adding the new cargo, the ship would be overweighted
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class ShipOverweightException extends Exception
{
    /**
     * Default constructor for an ShipOverweightException that
     * passes a default string to the Exception class constructor.
     *
     * @custom.Postcondition:
     *    The object is created and contains the default message.
     */
    public ShipOverweightException()
    {
        //Default Message
        super("Operation failed! Cargo would put ship overweight.");
    }

    /**
     * Second constructor for the ShipOverweightException that
     * passes a provided string to the Exception class constructor.
     *
     * @param errorMessage
     *    the message that the object is to contain
     *    
     * @custom.Postcondition:
     *    The object is created and contains the provided message.
     */
    public ShipOverweightException(String errorMessage)
    {
        //Passed Message
        super(errorMessage);
    }
}
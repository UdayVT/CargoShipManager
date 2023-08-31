/**
 * An exception thrown in the <CODE>CargoShip</CODE> class to indicate that 
 * the stack is currently at its maximum height
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class FullStackException extends Exception
{
    /**
     * Default constructor for an FullStackException that
     * passes a default string to the Exception class constructor.
     *
     * @custom.Postcondition:
     *    The object is created and contains the default message.
     */
    public FullStackException()
    {
        //Default Message
        super("Operation failed! Cargo stack is at maximum height.");
    }

    /**
     * Second constructor for the FullStackException that
     * passes a provided string to the Exception class constructor.
     *
     * @param errorMessage
     *    the message that the object is to contain
     *    
     * @custom.Postcondition:
     *    The object is created and contains the provided message.
     */
    public FullStackException(String errorMessage)
    {
        //Passed Message
        super(errorMessage);
    }
}
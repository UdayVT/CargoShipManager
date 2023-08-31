/**
 * An exception thrown in the <CODE>CargoShip</CODE> class to indicate that 
 * the stack has no cargo items on it
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class EmptyStackException extends Exception
{
    /**
     * Default constructor for an EmptyStackException that
     * passes a default string to the Exception class constructor.
     *
     * @custom.Postcondition:
     *    The object is created and contains the default message.
     */
    public EmptyStackException()
    {
        //Default Message
        super("Operation failed! No Cargo are on the stack.");
    }

    /**
     * Second constructor for the EmptyStackException that
     * passes a provided string to the Exception class constructor.
     *
     * @param errorMessage
     *    the message that the object is to contain
     *    
     * @custom.Postcondition:
     *    The object is created and contains the provided message.
     */
    public EmptyStackException(String errorMessage)
    {
        //Passed Message
        super(errorMessage);
    }
}
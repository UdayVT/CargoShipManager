/**
 * A stack data structure for Cargo with common functionalities such as 
 * push, pop, size, and isEmpty. 
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 21th, 2023
 */
public class CargoStack 
{
    private String name;
    private int capacity;//the maximum stack size
    private int topIndex;//the index of top of the stack
    private Cargo[] stack;// The stack
    
    /**
     * Default Constructor of Cargo Stack
     * @param initCapacity
     *     Maximum capacity of stack.
     */
    public CargoStack(String initName, int initCapacity)
    {
        this.name = initName;
        this.capacity = initCapacity;
        this.stack = new Cargo[capacity];
        this.topIndex = 0;
    }

    /**
     * Pushes a cargo on top of the stack if it does not
     * exceeds the capacity of the stack
     * @param cargo
     *      The cargo thats to be pushed
     * @custom.Precondition
     *      If the stack being pushed to is at the max height.
     *      If the cargo would be stacked on top of a weaker cargo
     * @throws FullStackException
     *      If the stack being pushed to is at the max height.
     */
    public void push(Cargo cargo) throws FullStackException, CargoStrengthException
    {
        if(this.topIndex == this.capacity)
            throw new FullStackException();

        if(topIndex != 0 && stack[topIndex-1].getStrength().getStrengthValue() < cargo.getStrength().getStrengthValue())
            throw new CargoStrengthException();

        this.stack[topIndex++] = cargo;
    }

    /**
     * Pops the top of the stack
     * @return
     *      The cargo thats removed from stack
     */
    public Cargo pop()
    {
        return this.stack[--topIndex];
    }

    /**
     * Returns the top of the stack without removing it
     * @return
     *      The top of the stack without removing it
     * @throws EmptyStackException
     *      if the stack being popped from is empty.
     */
    public Cargo peek() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        return this.stack[topIndex-1];
    }

    /**
     * Returns the amount of cargo in the stack
     * @return
     *      amount of cargo in the stack
     */
    public int size()
    {
        return this.topIndex;
    }

    /**
     * Returns true if there are no cargo in the stack, otherwise false
     * @return
     *      true or false depending on the answer
     */
    public boolean isEmpty()
    {
        return this.topIndex==0;
    }

    /**
     * Overwrites the toString function of Object
     * @return
     *      the stack in a format of cargo strength
     */
    public String toString()
    {
        String answer = "";
        answer += this.name+":";

        for(int i = 0; i<topIndex;i++)
        {
            answer += " "+ stack[i].getStrength().name().charAt(0);
            
            if(i != topIndex-1)
            {
                answer += ",";
            }
        }
        return answer;
    }

    /**
     * Creates a clone of the cargoStack
     * @return 
     *      clone of the cargo stack with same way the cargo are arranged
     */
    public CargoStack clone()
    {
        CargoStack newCargoStack = new CargoStack(this.name, this.capacity);
        newCargoStack.topIndex = this.topIndex;

        for(int i = 0; i<this.size();i++)
        {
            newCargoStack.stack[i] = this.stack[i].clone();
        }

        return newCargoStack;
    }

    /**
     * Sets the topIndex to a new one
     * @param topIndex
     *      the new top index
     */
    public void setTopIndex(int topIndex) 
    {
        this.topIndex = topIndex;
    }
}

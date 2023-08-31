/**
 * An abstract data type meant to serve as a representation of a cargo ship
 * containing x amount of Cargo stacks.
 * 
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 22th, 2023
 */
public class CargoShip 
{
    private CargoStack[] stacks;
    private int maxHeight;
    private double maxWeight;
    private double currentWeight;
    
    /**
     * Default Constructor.
     * @param numStacks
     *      The number of stacks this ship can support. This parameter should be used to initialize the stacks array to a fixed size.
     * @param initMaxHeight
     *      The maximum height of any stack on this ship.
     * @param initMaxWeight
     *      The maximum weight for all the cargo on the ship.\
     * @custom.Precondition
     *      numStacks > 0.
     *      initMaxHeight > 0.
     *      initMaxWeight > 0.
     * @custom.Postcondition
     *      This object has been initialized to a CargoShip object with the indicated number of stacks, maxHeight, and maxWeight.
     * @throws IllegalArgumentException
     *      if either of the parameters are now within the appropriate bounds.
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) throws IllegalArgumentException
    {
        //Precondition checks
        if(numStacks <= 0)
            throw new IllegalArgumentException("The number of stacks in ship cannot be less than or equal to 0.");
        
        if(initMaxHeight <= 0)
            throw new IllegalArgumentException("The maximum height of the stack cannot be less than or equal to 0");
        
        if(initMaxWeight <= 0)
            throw new IllegalArgumentException("The maximum weight of the stack cannot be less than or equal to 0");


        this.stacks = new CargoStack[numStacks];
        this.maxHeight = initMaxHeight;
        this.maxWeight = initMaxWeight;

        for(int i = 0; i < numStacks; i++)
        {
            stacks[i] = new CargoStack("Stack "+(i+1), maxHeight);
        }
    }

    /**
     * Pushes a cargo container to the indicated stack on the cargo ship
     * @param cargo
     *      The container to place on the stack.
     * @param stack
     *      The index of the stack on the ship to push cargo onto.
     * @custom.Precondition
     *      This CargoShip is initialized and not null.
     *      cargo is initialized and not null.
     *      1 ≤ stack ≤ stacks.length.
     *      The size of the stack at the desired index is less than maxHeight.
     *      The total weight of all Cargo on the ship and cargo.getWeight()is less than maxWeight
     * @custom.Postcondition
     *      The cargo has been successfully pushed to the given stack, 
     *      or the appropriate exception has been thrown, in which case the state of the cargo ship should remain unchanged.
     * @throws IllegalArgumentException
     *      If cargo is null or stack is not in the appropriate bounds.
     * @throws FullStackException
     *      If the stack being pushed to is at the max height.
     * @throws ShipOverweightException
     *      If cargo would make the ship exceed maxWeight and sink.
     * @throws CargoStrengthException
     *      If the cargo would be stacked on top of a weaker cargo 
     */
    public void pushCargo(Cargo cargo, int stack) throws IllegalArgumentException, FullStackException, 
    ShipOverweightException, CargoStrengthException
    {
        //Precondition checks
        if(cargo == null)
            throw new IllegalArgumentException("Cargo cannot be null");
        
        if(stack<0 || stack>this.stacks.length)
            throw new IllegalArgumentException("You cannot add to a non-existant stack");
        
        if(cargo.getWeight()+this.currentWeight > this.maxWeight)
            throw new ShipOverweightException();

        this.stacks[stack].push(cargo);
        this.currentWeight += cargo.getWeight();

    }

    /**
     * Pops a cargo from one of the specified stack.
     * @param stack
     *      The index of the stack to remove the cargo from.
     * @custom.Precondition
     *      This CargoShip is initialized and not null.
     *      1 ≤ stack ≤ stacks.length.
     * @custom.Postcondition
     *      The cargo has been successfully been popped from the stack, and returned, 
     *      or the appropriate exception has been thrown, in which case the state of the cargo ship should remain unchanged.
     * @return
     *      The cargo that has been removed from stack
     * @throws IllegalArgumentException
     *      If stack is not in the appropriate bounds.
     * @throws EmptyStackException
     *      If the stack being popped from is empty.
     */
    public Cargo popCargo(int stack) throws IllegalArgumentException, EmptyStackException
    {
        if(stack<0 || stack>this.stacks.length)
            throw new IllegalArgumentException("You cannot add to a non-existant stack");
        
        if(stacks[stack].size() == 0)
            throw new EmptyStackException();
        
        Cargo popCargo = this.stacks[stack].pop();
        currentWeight -= popCargo.getWeight();
        
        return popCargo;
    }

    /**
     * Finds and prints a formatted table of all the cargo on the ship with a given name. 
     * If the item could not be found in the stacks, notify the user accordingly. 
     * @param name
     *      The name of the cargo to find and print.
     * @custom.Precondition
     *      This CargoShip is initialized and not null.
     * @custom.Postcondition
     *      The details of the cargo with the given name have been found and printed, 
     *      or the user has been notified that no such cargo has been found.
     *      The state of the cargo ship should remain unchanged.
     */     
    public void findAndPrint(String name)
    {
        String answer = "";
        answer += "\n Stack   Depth   Weight   Strength\n";
        answer += "=======+=======+========+==========\n";

        int count = 0;
        double weight = 0;
        for(int i = 0; i < stacks.length; i++)
        {
            CargoStack tempStack = stacks[i].clone();

            int depth = 0;
            while(!tempStack.isEmpty())
            {
                Cargo tempCargo = tempStack.pop();
                if(tempCargo.getName().equals(name))
                {
                    answer += "   "+(i+1)+"   |   "+depth+"   |  "+(int)tempCargo.getWeight()+"  |  "+tempCargo.getStrength()+"  \n";
                    count++;
                    weight += tempCargo.getWeight();
                }
                depth++;   
            }            
        }
     
        if(count == 0)
        {
            System.out.println("Cargo \'"+name+"\' could not be found on the ship.");
            return;
        }

        answer += "\nTotal Count:  "+count;
        answer += "\nTotal Weight: "+(int)weight;

        System.out.println(answer);
    }

    /**
     * Prints the ship's all stack by calling CargoStacks toString function
     */
    public void printShip()
    {
        for(int i = 0; i<stacks.length; i++)
        {
            System.out.println(stacks[i]);
        }
    }

    /**
     * Moves cargo from one stack to another
     * @param from
     *      the stack cargo will be moved from
     * @param to
     *      the stack from which cargo will be moved to
     * @return
     *      the cargo that has been moved
     * @throws EmptyStackException
     *      if the stack is empty from which cargo is being moved from
     * @throws IllegalArgumentException
     *      if a value of from or to is given which does not work
     * @throws FullStackException
     *      if the stack is full where cargo is being moved to
     * @throws CargoStrengthException
     *      If the cargo would be stacked on top of a weaker cargo.
     */
    public Cargo moveCargo(int from, int to) throws EmptyStackException, IllegalArgumentException, FullStackException, 
    CargoStrengthException,ShipOverweightException
    {
        if(from < 0 || from >= stacks.length || to < 0 || to >= stacks.length || from==to)
        {
            throw new IllegalArgumentException("Invalid index input");
        }

        Cargo pop = stacks[from].peek();
        pushCargo(pop, to);
        popCargo(from);

        return pop;
    }

    /**
     * Returns the current weight of cargo ship.
     * @return
     *      current weight of cargo ship
     */
    public double getCurrentWeight() 
    {
        return this.currentWeight;
    }

    /**
     * returns the maximum weight of cargo ship.
     * @return
     *      maximum weight allowed on the cargo ship 
     */
    public double getMaxWeight() 
    {
        return this.maxWeight;
    }

    /**
     * Returns the top Cargo of stack
     * @param stack
     *      The stack to peek into
     * @return
     *      the cargo at the top of stack selected
     * @throws EmptyStackException
     *      if the selected stack is empty
     * @throws IllegalArgumentException
     *      if an invalid input is given to select a stack
     */
    public Cargo peekCargo(int stack) throws EmptyStackException,IllegalArgumentException
    {
        if(stack < 0)
        {
            throw new IllegalArgumentException("Invalid index input");
        }
        return stacks[stack].peek();
    }

    /**
     * Returns all the stack of cargos
     * @return
     *      the stacks of cargo in the ship
     */
    public CargoStack[] getStacks() 
    {
        return stacks;
    }
}


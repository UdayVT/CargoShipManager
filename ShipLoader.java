/**
 * An driver program used to test out the <CODE>CargoShip</CODE> with stacks
 * written as part of this assignment.  The user can use the commands below
 * to perform operations on <CODE>CargoShip</CODE> objects.
 * <dt><b>Commands:</b><dd> 
 *      C) Create new cargo <name> <weight> <strength>
        L) Load cargo from dock <stackIndex>
        U) Unload cargo from ship <srcStackIndex>
        M) Move cargo between stacks <srcStackIndex> <dstStackIndex>
        K) Clear dock
        P) Print ship stacks
        S) Search for cargo <name>
        Q) Quit
 * @author 
 * 		Uday Turakhia, SBU ID #: 115102637
 * <dt><b>Assignment:</b><dd>
 *    Homework #3 for CSE 214, Spring 2023
 * 		Recitation #: R03
 * <dt><b>Date:</b><dd>
 *    February 23th, 2023
 */
import java.util.Scanner;
public class ShipLoader 
{
    public static CargoStack dock;//The dock stack
    public static Scanner input = new Scanner(System.in);//input
    public static CargoShip ship;//The Cargo ship


    /**
     * A method created for simplicity of Scanner
     * @return
     *      The input from user
     */
    public static int nextIntLine()
    {
        int answer = input.nextInt();
        input.nextLine();
        return answer;
    }

    /**
     * A method created for simplicity of Scanner
     * @return
     *      The input from user
     */
    public static double nextDoubleLine()
    {
        double answer = input.nextDouble();
        input.nextLine();
        return answer;
    }
    
    public static void printShip()
    {
        ship.printShip();
        System.out.println(dock);
        System.out.printf("\nTotal Weight = %.0f / %.0f\n",ship.getCurrentWeight(),ship.getMaxWeight());
    }

    /**
     * Asks user for parameters of the CargoShip
     * @return
     *      if the ship was built or not
     */
    public static boolean buildShip()
    {
        System.out.println("\nCargo Ship Parameters");
        System.out.println("--------------------------------------------------");
        System.out.print("Number of stacks: ");
        int numStacks = nextIntLine();
        System.out.print("Maximum height of stacks: ");
        int maxHeight = nextIntLine();
        System.out.print("Maximum total cargo weight: ");
        double maxWeight = nextDoubleLine();

        try 
        {
            ship = new CargoShip(numStacks, maxHeight, maxWeight);   
            System.out.println("\nCargo ship created.");
            System.out.println("Pulling ship in to dock...");
            System.out.println("Cargo ship ready to be loaded.");
            dock = new CargoStack("Dock", 9999);
            return true;
            
        } catch (IllegalArgumentException e) 
        {
            System.out.println("\n"+e.getMessage());
        }
        return false;
    }

    /**
     * A method which ouputs the menu for the user and ask for input
     * @return
     *      Returns the option selected by the user
     */
    public static char getOption()
    {
        System.out.println("\nPlease select an option:\n"
                            +"C) Create new cargo\n"
                            +"L) Load cargo from dock\n"
                            +"U) Unload cargo from ship\n"
                            +"M) Move cargo between stacks\n"
                            +"K) Clear dock\n"
                            +"P) Print ship stacks\n"
                            +"S) Search for cargo\n"
                            +"R) Remove cargo from ship\n"
                            +"Q) Quit\n");
            System.out.print("Select a menu option: ");
            String option = input.nextLine().toUpperCase();
            System.out.println();
        
            if(option.length()==0)
            {
                return 'O';
            }
            return option.charAt(0);
    }

    /**
     * New cargo ship being created from user and loading it to dock
     */
    public static void createNewCargo()
    {
        System.out.print("Enter the name of the cargo: ");
        String name = input.nextLine();
        System.out.print("Enter the weight of the cargo: ");
        double weight = nextDoubleLine();

        System.out.print("Enter the container strength (F/M/S): ");
        char strength = input.nextLine().toUpperCase().charAt(0);
        CargoStrength cStrength;
        if(strength == 'F')
        {
            cStrength = CargoStrength.FRAGILE;
        }
        else if(strength == 'M')
        {
            cStrength = CargoStrength.MODERATE;
        }
        else if(strength == 'S')
        {
            cStrength = CargoStrength.STURDY;
        }
        else
        {
            System.out.println("Invalid input, Cargo strength input should be F,M or S");
            return;
        }

        try 
        {
            Cargo cargo = new Cargo(name, weight, cStrength);
            dock.push(cargo);
            System.out.println("\nCargo \'"+name+"\' pushed onto the dock.\n");
            printShip();
        } catch (IllegalArgumentException | FullStackException | CargoStrengthException e) 
        {
            System.out.println("\n"+e.getMessage());
        }
    }

    /**
     * Clearing the dock.
     */
    public static void clearDock()
    {
            dock.setTopIndex(0);
            System.out.println("\nDock cleared.\n");
            printShip();
    }

    /**
     * Loading the top of dock to the Cargo if possible
     */
    public static void loadCargoFromDock()
    {
        System.out.print("Select the load destination stack index: ");
        int loadTo = nextIntLine();

        try 
        {
            Cargo cargo = dock.peek();
            ship.pushCargo(cargo, loadTo-1);
            dock.pop();
            System.out.println("\nCargo \'"+cargo.getName()+"\' moved from dock to stack "+loadTo+".\n");
            printShip();
        } catch (Exception e) 
        {
            System.out.println("\n"+e.getMessage());
        }
    }

    /**
     * Unloading the selected stack's top cargo to dock
     */
    public static void unloadCargoFromStack()
    {
        System.out.print("Select the unload source stack index: ");
        int unloadFrom = nextIntLine();

        try 
        {
            Cargo cargo = ship.peekCargo(unloadFrom-1);
            dock.push(cargo);
            ship.popCargo(unloadFrom-1);
            System.out.println("\nCargo \'"+cargo.getName()+"\' moved from stack "+unloadFrom+" to dock.\n");
            printShip();
        } catch (Exception e) 
        {
            System.out.println("\n"+e.getMessage());
        }
    }

    /**
     * Moves from one stack to another
     */
    public static void moveCargoBetweenStacks()
    {
        System.out.print("Source stack index: ");
        int from = nextIntLine();
        System.out.print("Destination stack index: ");
        int to = nextIntLine();

        try 
        {
            Cargo cargo = ship.moveCargo(from-1,to-1);
            System.out.println("Cargo \'"+cargo.getName()+"\' moved from stack "+from+" to stack "+to+".\n");
            printShip();
        } catch (Exception e) 
        {
            System.out.println("\n"+e.getMessage());
        }
    }

    /**
     * Searches for the cargo and prints if found
     */
    public static void searchForCargo()
    {
        System.out.print("Enter the name of the cargo: ");
        ship.findAndPrint(input.nextLine());
        System.out.println();
    }

    /**
     * Removes the cargo from the ship
     */
    public static void RemoveCargo()
    {
        System.out.print("Enter the name of the cargo: ");
        String name = input.nextLine();

        dock.setTopIndex(0);
        System.out.println("\nDock cleared.");
        System.out.println("Removing cargo \'"+name+"\'.\n");
        
        int steps = 0;

        for(int i = 0; i < ship.getStacks().length; i++)
        {
            CargoStack tempStack = ship.getStacks()[i].clone();

            int depth = 0;
            int moveTo = 0;
            while(!tempStack.isEmpty())
            {
                Cargo tempCargo = tempStack.pop();
                if(tempCargo.getName().equals(name))
                {
                    if(depth == 0)
                    {
                        try
                        {
                            Cargo pop = ship.popCargo(i);
                            dock.push(pop);
                        }
                        catch(Exception e)
                        {
                            System.out.println("\nSomething went wrong\n");
                        }

                        steps++;
                        System.out.println("\nStep "+steps+": Moved cargo from stack "+ (i+1)+" to dock\n");
                        printShip();
                    }
                    else
                    {
                        while(true)
                        {
                            try 
                            {
                                ship.moveCargo(i, moveTo);
                                steps++;
                                System.out.println("\nStep "+steps+": Moved cargo from "+(i+1)+" to "+(moveTo+1)+"\n");
                                i = -1;
                                printShip();
                                break;
                            } catch (Exception e) 
                            {
                                moveTo++;
                                if(moveTo == ship.getStacks().length)
                                {
                                    moveTo = 0;
                                }
                            }
                        }
                    }
                }
                depth++;   
            }
        }

        if(steps == 0)
        {
            System.out.println("\nThe cargo could not be found on the ship\n");
        }
        else
        {
            System.out.println("\nCargo \'"+name+"\' removed from the ship.");
            System.out.println("Steps taken: "+steps+"\n");
        }
    }

    /**
     * Main function of the program
     */
    public static void main(String[] args) 
    {
        System.out.println("\nWelcome to ShipLoader!");
        while(!buildShip());

        boolean quit = false;
        while (!quit) 
        {
            char option = getOption();
            switch (option) 
            {
                case 'C':
                    createNewCargo();
                    break;
                case 'L':
                    loadCargoFromDock();
                    break;
                case 'U':
                    unloadCargoFromStack();
                    break;
                case 'M':
                    moveCargoBetweenStacks();
                    break;
                case 'K':
                    clearDock();
                    break;
                case 'P':
                    printShip();
                    break;
                case 'S':
                    searchForCargo();
                    break;
                case 'R':
                    RemoveCargo();
                    break;
                case 'Q':
                    System.out.println("Program terminating normally...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }
}
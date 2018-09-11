import java.util.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Diar Sabri
 * @version 1
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack roomOrder = new Stack();
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, kitchen, bedroom, livingroom, hallway, cellar, attic, traproom;
      
        // create the rooms
        hallway = new Room("in the hallway");
        outside = new Room("outside the house");
        outside.addItemtoRoom("strange looking key", 5);
        kitchen = new Room("in the kitchen");
        kitchen.addItemtoRoom("sandwich", 10);
        bedroom = new Room("in the bedroom");
        bedroom.addItemtoRoom("map", 10);
        livingroom = new Room("in the livingroom");
        cellar = new Room("in the cellar");
        cellar.addItemtoRoom("magic cookie", 0);
        attic = new Room("in the attic");
        traproom = new Room("trapped in here");
        
        
        
        // initialise room exits
        hallway.setExit("south", outside);
        cellar.setExit("up", kitchen);
        hallway.setExit("east", kitchen);
        hallway.setExit("north", bedroom);
        hallway.setExit("west", livingroom);
        bedroom.setExit("up", attic);
        outside.setExit("down", cellar);
        outside.setExit("north", hallway);
        kitchen.setExit("west", hallway);
        bedroom.setExit("south", hallway);
        livingroom.setExit("east", hallway);
        attic.setExit("up", traproom);
        
        
        currentRoom = outside;  // start game outside
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void eat()
    {
        System.out.println("You have eaten, you are not hungry annymore");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        
        printLocationInfo();
        System.out.println();
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        
        String commandWord = command.getCommandWord();
        String commandWordtwo = command.getSecondWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("back")) {
            goBack(command);
        }
        
        return wantToQuit;
    }
    
    // implementations of user commands:
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }
    
    /**
     * method to go back
     */
    public void goBack(Command command)
    {
        if(currentRoom.getExitString() == "NoWayOut") {
            System.out.println("You have lost the game" + ".\n" + "type quit to exit the game");
        }
        else if(roomOrder.empty()) {
            System.out.println("You can't go back there");
        }
        else {
            
            currentRoom = (Room) roomOrder.pop();
            System.out.println(currentRoom.getLongDescription());
         }
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomOrder.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    
    /**
     * 
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
        
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
}

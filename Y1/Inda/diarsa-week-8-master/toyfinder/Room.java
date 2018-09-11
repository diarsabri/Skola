import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item roomitem;
    private Item item;
    private ArrayList<Item> items;
    private HashMap<String, Item> itemsX;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room’s exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String x = "Exits: ";
        Set<String> keys = exits.keySet();
        if(keys.isEmpty()) {
            return "NoWayOut";
        }
        else {
         for(String exit : keys) {
            x += " " + exit;
         }
        }
        
        return x;
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * returns a long description of the room u're in
     */
    public String getLongDescription()
    {
        item = this.roomitem;
        return "You are " +description + ".\n" +getExitString() +".\n" + getallItems();
    }
    
    /**
     * a room can now hold any number of items
     */
    public void addItemtoRoom(String description, int weight)
    {
        Item item = new Item(description, weight);
        items.add(item);
        
    }
    
    /**
     * returns all items in the room
     */
    public String getallItems()
    {
        String x = "Items in this room: ";
        for(int i = 0; i<items.size();i++) {
            x += items.get(i).getItemDesc(); 
        }
        
        if(items.isEmpty()) {
            x = "Empty room";
        }
        return x;
    }
}

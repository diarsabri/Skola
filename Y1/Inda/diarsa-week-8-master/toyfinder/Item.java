import java.util.*;
/**
 * 
 */
public class Item
{
    private String itemDescription;
    private int itemWeight;
    private HashMap<String, Item> items;
    
    public Item(String itemDescription, int itemWeight)
    {
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        items = new HashMap<String, Item>();
    }
    
    public String getItemDesc() 
    {
        String x = "There is a ";
        x += itemDescription +" ,it weighs: "+itemWeight + ".\n";
        return x;
    }
}

import java.util.ArrayList;

/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StockManager
{
    // A list of the products.
    private ArrayList<Product> stock;

    /**
     * Initialise the stock manager.
     */
    public StockManager()
    {
        stock = new ArrayList<>();
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {
        stock.add(item);
    }
    
    /**
     * 4.56
     */
    public void printProductDetails()
    {
        for(Product product : stock) {
            System.out.println(product.toString());
        }
    }
    
    /**
     * 4.57
     */
    public Product findProduct(int id)
    {
        for(Product product : stock) {
            if(product.getID() == id) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * 4.58
     */
    public int numberInStock(int id)
    {
        for(Product product : stock) {
            if(product.getID() == id) {
                return product.getQuantity();
            }
        }
        return 0;
    }
    
    /**
    * 4.59
    */
    public void delivery(int id, int amount)
    {
        for(Product product : stock) {
            if(product.getID() == id) {
                product.increaseQuantity(amount);
            }
        }
    }
}
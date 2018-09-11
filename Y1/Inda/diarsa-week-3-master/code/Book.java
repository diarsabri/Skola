/**
 * A class that maintains information on a book.
 * This might form part of a larger application such
 * as a library system, for instance.
 *
 * @author (Insert your name here.)
 * @version (Insert today's date here.)
 */
class Book
{
    // The fields.
    private String author;
    private String title;
    private int pages;
    private String refNumber;
    private int borrowed;

    /**
     * Set the author and title fields when this object
     * is constructed.
     */
    public Book(String bookAuthor, String bookTitle, int pageNumber)
    {
        author = bookAuthor;
        title = bookTitle;
        pages = pageNumber;
        refNumber = ("");
    }
    
    /**
     * Returns author
     */
    public String getAuthor() 
    {
        return author; 
    }
    
    /**
     * Returns title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * prints the authors name
     */
    public void printAuthor()
    {
        System.out.println("Author: " +author );
    }
    
    /**
     * prints the title name
     */
    public void printTitle()
    {
        System.out.println("Title: " +title );
    }
    
    /**
     * Returns amount of pages
     */
    public int getPages()
    {
        return pages;
    }
    /**
     * Exercise 2.x: the objects are currently immutable since all methods to this point
     * only return values/strings.
     */
    
   /**
    * prints all the details
    */
    public void printDetails()
    {
        System.out.println("Author: " +author );
        System.out.println("Title: " +title );
        System.out.println("Pages: " +pages );
        
        if( refNumber.length() >0) {
            System.out.println("Reference Number:" +refNumber );
        }
        else {
            System.out.println("ZZZ");
        }
        System.out.println("Amount of times this book has been borrowed:" +borrowed);
        System.out.println("");
    }
    
   /**
    * assigns the value of the parameter to the refNumber
    */
   public void setRefNumber(String ref)
   {
       if(ref.length() >3) {
           refNumber = ref;
        }
        else {
            System.out.println("Error");
        }
    }
   
   /**
    * accessor method for the mutator above to return the refNumber
    */
   public String getRefNumber()
   {
       return refNumber;
    }
    
    /**
     * borrowed mutator
     */
   public void borrowOnce()
   {
       borrowed = borrowed + 1;
    }
    
    /**
     * returns the borrowed total count
     */
   public int borrowedAmountOfTimes()
   { 
       return borrowed;
    }
}

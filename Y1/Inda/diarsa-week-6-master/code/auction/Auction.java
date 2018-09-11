import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }
    
    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                                   lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                Bid highestBid = selectedLot.getHighestBid();
                System.out.println("Lot number: " + lotNumber +
                                   " already has a bid of: " +
                                   highestBid.getValue());
            }
        }
    }

    /**
     * 4.51
     */
    public Lot getLot(int lotNumber)
    {
        for(Lot chosenLot : lots) {
            if(chosenLot.getNumber() == lotNumber) {
                return chosenLot;
            }
        }
            return null;
    }
    
    /**
     * 4.48
     */
    public void close()
    {
        for(Lot lots : lots) {
            Bid checkIfNull = lots.getHighestBid();
            if(checkIfNull != null) {
                System.out.println("The lot currently has a bid by: " + lots.getHighestBid().getBidder().getName());
                System.out.println("And the highest bid is: " + checkIfNull.getValue());
            }
            else {
                System.out.println(" There is currently no bid on the lot");
            }
        }
    }
    
    /**
     * 4.49
     */
    
    public ArrayList<Lot> getUnsold()
    {
        Bid checkIfSold;
        ArrayList unsoldList = new ArrayList();
        for(Lot lots : lots) {
            checkIfSold = lots.getHighestBid();
            if( checkIfSold == null) {
                unsoldList.add(lots);
            }
        }
        return unsoldList;
    }
    
    /**
     * 4.52
     */
    
    public Lot removeLot(int number)
    {
        int k;
        Lot kLot;
        for(k = lots.size() -1; k >= 0; k = k -1) {
            kLot = lots.get(k);
            if(kLot.getNumber() == number) {
                lots.remove(k);
                return kLot;
            }
        }
        return null;
    }
}
        
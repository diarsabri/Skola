import java.util.Random;
import java.util.ArrayList;
/**
 * RandomTester, class that can generate random numbers
 * @author Diar Sabri
 * @version 1.0
 */
public class RandomTester
{
    public Random randomGen;
    public ArrayList<String> response;
    public RandomTester()
    {
        randomGen = new Random();
    }
    
    /**
     * returns a random int
     * 
     * 5.14
     */
    public int printOneRandom()
    
    {
        int i = randomGen.nextInt();
        return i;
        
    }
    
    /**
     * prints a given random number of ints
     * @param howMany is an int given by the user to define how many number of random ints are printed
     * 
     * 5.14
     */
    public void printMultiRandom(int howMany)
    {
        for(int i = 0; i < howMany; i++) {
            int k = randomGen.nextInt();
            System.out.println(k);
        }
    }
    
    /**
     * returns int between 1-6
     * @return int i returns a number between 1 to 6
     * 
     * 5.16
     */
    public int throwDice()
    {
        int i = randomGen.nextInt(7);
        while(i == 0) {
            i = randomGen.nextInt(7);
        }
        return i;
    }
    
    /**
     * returns one of the three strings
     * @return Returns one of the three given strings in the ArrayList
     * 
     * 5.17
     * 5.18
     */
    public String getResponse()
    {
        String y = "yes";
        String n = "no";
        String m = "maybe";
        response = new ArrayList<String>();
        response.add(y);
        response.add(n);
        response.add(m);
        int x = randomGen.nextInt(3);
        
        if(x == 0) {
            return y;
        }
        else if(x == 1) {
            return n;
        }
        else {
            return m;
        }
    }
    
    /**
     * takes random maxnumber and generates a random number of 0-max
     * @return int i returns a randomly generated number between the parameter max and 0
     * @param max is the highest int possible between itself and 0 that will be generated.
     * 
     * 5.19
     */
    public int randomMax(int max)
    {
        int i = randomGen.nextInt(max+1);
        return i;
    }
    
    /**
     * takes 2 params, returns random int between them
     * @param min is the lowest int given by the user
     * @param max is the highest int given by the user
     * @return x is the returned int in the interval between max and min. max inclusive
     * 
     * 5.20
     */
    public int randomMinMax(int min,int max)
    {
        int x = max - min +1;
        x = randomGen.nextInt(x);
        return x;
    }
    
}

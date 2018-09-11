
/**
 * generates a name based on given input
 * 
 * @author Diar Sabri
 * @version 1
 * only has one method
 * @param firstName,lastName,maidenName,birthCity, all of these are given by the user.
 * @return returns the wanted number of letters according to the exercise from the given input 
 * 5.71
 */
public class NameGenerator
{
    public String generateStarWarsName(String firstName, String lastName, String maidenName, String birthCity)
    {
        String x = lastName.substring(0,3) + firstName.substring(0,2);
        String y = maidenName.substring(0,2) + birthCity.substring(0,3);
        
        return x +"  " + y;
    }
}

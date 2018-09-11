/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * 4.74
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour <= hourCounts.length -1; hour++)
        {
        total = total + hourCounts[hour];
        }
        return total;
    }
    
    /**
     * 4.75
     */
    public int busiestHour()
    {
        int maxHour = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[maxHour])
            {
            maxHour = hour;
            }
        }
        return maxHour;
    }
    
    /**
     * 4.76
     */
    public int quietestHour()
    {
        int minHour = 0;
        for(int hour = 1; hour < hourCounts.length -1; hour++) {
            if(hourCounts[hour] < hourCounts[minHour])
            if(hourCounts[hour] > 0)
            minHour = hour;
        }
        return minHour;
    }
    
    /**
     * 4.78
     */
    public int LogAnalyzer()
    {
        int accessCount = 0;
        int topHour = 0;
        int k = 0;
        while(k < hourCounts.length -1) {
            if( accessCount < hourCounts[k] + hourCounts[k +1]) {
                topHour = k;
                accessCount = hourCounts[k] + hourCounts[k +1];
                k++;
            }
            else {
                k++;
            }
        }
        return topHour;
    }
}
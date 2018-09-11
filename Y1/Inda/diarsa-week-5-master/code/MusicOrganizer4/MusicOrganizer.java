import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MusicOrganizer
{
    private ArrayList<String> files;
    private MusicPlayer player;
    
    public MusicOrganizer()
    {
        files = new ArrayList<>();
        player = new MusicPlayer();
    }

    public void addFile(String filename)
    {
        files.add(filename);
    }

    public int getNumberOfFiles()
    {
        return files.size();
    }

    public void listFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    public void listAllFiles()
    {
        for(String filename : files) {
            System.out.println(filename);
        }
    }

    public void listMatching(String searchString)
    {
        for(String filename : files) {
            if(filename.contains(searchString)) {
                // A match.
                System.out.println(filename);
            }
        }
    }

    public int findFirst(String searchString)
    {
        int index = 0;
        boolean searching = true;

        while(searching && index < files.size()) {
            String filename = files.get(index);
            if(filename.contains(searchString)) {
                searching = false;
            }
            else {
                index++;
            }
        }
        if(searching) {
            return -1;
        }
        else {
            return index;
        }
    }

    public void removeFile(int index)
    {
        if(index >= 0 && index < files.size()) {
            files.remove(index);
        }
    }

    public void startPlaying(int index)
    {
        String filename = files.get(index);
        player.startPlaying(filename);
    }

    public void stopPlaying()
    {
        player.stop();
    }
    
    /**
     * 4.30
     */
    
    public void multiplesOfFive()
    
    {
        int index = 10;
        while(index >= 10 && index <= 95)
         {
            System.out.println(index);
            index = index + 5;
        
         }
    }
    
    /**
     * 4.31
     */
    
    public void addertoTen()
    
    {
        int index = 0;
        int sum = 0;
      
        while(index <= 10)
      {
        sum = sum + index;
        index++;
      }
      System.out.println("Adding the numbers 0-10 gives you: " + sum);
    }
    
    /**
     * 4.32
     */
    
    public void sum(int valueA,int valueB)
    
    {
        int index;
        int sum = 0;
        while(valueA <= valueB)
        {
            index = valueA++;
            sum = sum + index;
        }
        {
            System.out.println(sum);
        }
    }
    
    /**
     * 4.33
     */
    
    public boolean isPrime(int n)
    
    {
      int div = 2;
        while(div <= n-1)
      {
        if(n % div == 0)
        {
          return false;
        }
        div++;
      }
         return true;
    }
    
}
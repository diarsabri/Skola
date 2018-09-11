import java.util.ArrayList;

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
        if(validIndex(index)) {
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

    public void removeFile(int index)
    {
        if(validIndex(index)) {
            files.remove(index);
        }
    }

    public void startPlaying(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            player.startPlaying(filename);
        }
    }

    public void stopPlaying()
    {
        player.stop();
    }

    public void playAndWait(int index)
    {
        if(validIndex(index)) {
            String filename = files.get(index);
            player.playSample(filename);
        }
    }

    private boolean validIndex(int index)
    {
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    /**
     * 4.26
     */
    
    public void listMatching(String searchString)
    
    {
     boolean noMatch = false;
     
        for(String filename : files)
       {
        if(filename.contains(searchString))
        {
            noMatch = true;
            System.out.println(filename);
        }
        {
        if(noMatch == false);
        System.out.println("The string has no match in the file");
        }
      }
    }
    
    /**
     * 4.27
     */
    
    public void artistPlaylist(String artistName)
    {
        for(String filename : files)
      {
        if(filename.contains(artistName))
        {
          player.playSample(filename);
        }
      }
    }
    
}

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
        if(index >= 0 && index < files.size()) {
            String filename = files.get(index);
            System.out.println(filename);
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
     * 4.24
     */
    public void listAllFiles()
    {
        int position = 0;
        for(String fileName : files)
      {
        System.out.println(position + ": " + fileName);
        position = position + 1;
      }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}

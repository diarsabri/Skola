import java.util.ArrayList;

public class MusicOrganizer
{
    private ArrayList<String> files;
    
    public MusicOrganizer()
    {
        files = new ArrayList<>();
    }
    
    public void addFile(String filename)
    {
        files.add(filename);
    }
    
    public int getNumberOfFiles()
    {
        return files.size();
    }
    
    /**
     * 4.16
     */
    
    public void listFile(int index)
    {
        if(validIndex(index) == true);
        String filename = files.get(index);
        System.out.println(filename);
    }

    /**
     * 4.16
     */
    public void removeFile(int index)
    {
        if(validIndex(index) == true); 
            files.remove(index);
    }
    
    /**
     * 4.14 uppdaterad
     */
    public void checkIndex(int indexNumber)
    {
        int idxMax = files.size() -1;
        
        if(idxMax == -1) {
            System.out.println("Empty Collection");
        }
        else if(!(indexNumber >= 0 && indexNumber <= idxMax)) {
            System.out.println("Collection is between index 0 and " +idxMax);
        }
    }
    
    /**
     * 4.15 uppdaterad
     */
    public boolean validIndex(int idx)
    {
        int i = files.size() -1;
        if(idx >= 0 && idx <= i)
        return true;
        else
        return false;
    }
}

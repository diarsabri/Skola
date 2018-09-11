import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class MusicOrganizer
{
    private ArrayList<Track> tracks;
    private MusicPlayer player;
    private TrackReader reader;
    
    public MusicOrganizer()
    {
        tracks = new ArrayList<>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("../audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    public void playTrack(int index)
    {
        if(indexValid(index)) {
            Track track = tracks.get(index);
            player.startPlaying(track.getFilename());
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
        }
    }
    
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    public void playFirst()
    {
        if(tracks.size() > 0) {
            player.startPlaying(tracks.get(0).getFilename());
        }
    }
    
    public void stopPlaying()
    {
        player.stop();
    }

    private boolean indexValid(int index)
    {
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    /**
     * 4.43
     */
    public void shuffleTrack()
   {
     Random shuffle = new Random();
     int shuffleInt = shuffle.nextInt(tracks.size());
     if(indexValid(shuffleInt))
     {
        Track shuffledtrack = tracks.get(shuffleInt);
        player.startPlaying(shuffledtrack.getFilename());
     }
   }
   
   /**
    * 4.45
    */
   
   public void shuffleOnce()
   {
    ArrayList<Track> shuffledList = tracks;
    Collections.shuffle(shuffledList);
    for(int index = 0; index <= shuffledList.size() -1; index = index +1) 
    {
    Track track = shuffledList.get(index);
    player.startPlaying(track.getFilename());
    }
    
   }
  
}
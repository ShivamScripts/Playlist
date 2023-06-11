import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class PlayList {
    private String title;
    private List<Song> songs;
    private boolean wasNext = false;
    private ListIterator<Song> itr;

    public PlayList(String title) {
        this.title = title;
        this.songs = new ArrayList<>();
        this.itr = songs.listIterator();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Song> getSongs() {
        return songs;
    }

   public String addSongFromAlbum(Album album ,String songName){
       Optional<Song> songOpt = album.searchSong(songName);
       if(songOpt.isEmpty()){
           return "song not found in album";
       }
       Song songInAlbum = songOpt.get();
       this.songs.add(songInAlbum);
       itr = songs.listIterator();
       return "song added successfully";
   }
   public String playNext(){
        if(!wasNext){
            wasNext = true;
            itr.next();
        }
        if(itr.hasNext()){
            wasNext = true;
            Song song = itr.next();
            return "next song is : "+song;
        }
        return "you have reached end of playlist";
   }
   public String playPrevious(){
        if(wasNext){
            wasNext = false;
            itr.previous();
        }
        if(itr.hasPrevious()){
            wasNext = false;
            Song song = itr.previous();
            return "Previous song is : "+song;
        }
        return "You have reached start of playlist. No song before this.";
   }
   public String currentSong(){
        if(wasNext){
            wasNext = false;
            return "current song is : "+itr.previous();
        }
        wasNext = true;
        return "current song is : "+itr.next();
   }
}

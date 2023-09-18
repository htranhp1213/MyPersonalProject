

/**
 *
 * @author huongtran
 */
public class DVD extends Product {
    private String artist;

    public DVD(String id, String title, String artist, double price, int stock) {
        super(id, title, price, stock);
        this.artist = artist;
    }


    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        //super.toString();
        return super.toString()+ " artist: " + artist + '}';
    }
    
    
    
}

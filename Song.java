import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author guetar
 */
public class Song
{
    private String name;
    //Invariante: laenge soll die Form "mm:ss" haben
    private String laenge;
    private GregorianCalendar von;
    private ArrayList<Song> versionen;
    
    //Vorbedingung: name!=null, laenge!=null, laenge soll die Form "mm:ss" haben
    //von!=null
    //Nachbedingung: name!=null, laenge!=null, von!=null, versionen!=null
    public Song(String name, String laenge, GregorianCalendar von) {
        this.name = name;
        this.laenge = laenge;
        this.von = von;
        this.versionen = new ArrayList<Song>();
        this.versionen.add(new Song(this));
    }
    
    //Vorbedingung: name!=null, laenge!=null, laenge soll die Form "mm:ss" haben
    //Nachbedingung: name!=null, laenge!=null
    public Song(String name, String laenge) {
        this.name = name;
        this.laenge = laenge;
    }
    
    //Vorbedingung: s!=null
    //Nachbedingung: name!=null, laenge!=null
    public Song(Song s) {
        this.name = s.name;
        this.laenge = s.laenge;
    }
    
    //Vorbedingung: s!=null
    //Nachbedingung: s wird zu versionen hinzugefuegt
    public Boolean versionHinzufuegen(Song s) {
        return versionen.add(s);
    }
    
    public ArrayList<Song> getVersionen() {
        return versionen;
    }
    
    public GregorianCalendar getVon() {
        return von;
    }
    
    @Override
    public String toString() {
        return name + " " + laenge;
    }
}

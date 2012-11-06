import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author steff
 */
public class SnapShot implements Comparable<SnapShot> {
    
    
    
    //IV: date && mitglieder != NULL
    private GregorianCalendar date;
    private HashSet<Mitglied> mitglieder;

    
    
    //VB: mitglieder && date != NULL
    //IV: mitglieder und date duerfen nicht veraendert werden.
    //NB: Das Objekt muss erfolgreich instanziert worden sein
    //    Die Variablen muessen tiefe Kopien der uebergebenen Objekte sein
    public SnapShot(HashSet<Mitglied> mitglieder, GregorianCalendar date) {
        this.mitglieder = new HashSet<Mitglied>(mitglieder);
        this.date = (GregorianCalendar) date.clone();
    }

    
    
    //VB: date != NULL
    //IV: date darf nicht geandert werden
    //NB: date muss als Kopie zurueckgegeben worden sein
    public GregorianCalendar getDate() {
        return (GregorianCalendar) date.clone();
    }
    
    
        
    //VB: mitglieder != NULL
    //IV: mitglieder darf nicht geandert werden
    //NB: mitglieder muss als Kopie zurueckgegeben worden sein
    public HashSet<Mitglied> getMitglieder() {
        return new HashSet<Mitglied>(mitglieder);
    }
    
    
    
    //VB: s && this != NULL
    //IV: this darf nicht veraendert werden
    //NB: Rueckgabe sinngemeasser Information ueber Reihung
    public int compareTo(SnapShot s) {
        if (s != null) {
            if (this.date.before(s.getDate())) {
                return -1;
            } else if (this.date.after(s.getDate())) {
                return 1;
            }
            return 0;
        } else {
            return 0;
        }
    }
}

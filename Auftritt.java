import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Auftritt extends Termin {

    //Vorbedingung: ort, datum, dauer, teilnehmer und gage != null
    public Auftritt(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int gage) {
        super((gage > 0) ? gage : -gage, ort, datum, dauer, teilnehmer);
    }
    
    //Vorbedingung: Termin != null
    public Auftritt(Termin t) {
        super(t);
    }
    
    @Override
    //Nachbedingung: return = 1
    public int getKategorie() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString() + " " + super.getWert() + " Euro Gage";
    }
}

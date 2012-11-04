import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Auftritt extends Termin {

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer, teilnehmer und gage sollten nicht null sein.
     */
    public Auftritt(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int gage) {
        super((gage > 0) ? gage : -gage, ort, datum, dauer, teilnehmer);
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebener Auftritt sollte nicht null sein.
     */
    public Auftritt(Termin t) {
        super(t);
    }
    
    @Override
    public int getKategorie() {
        return 2;
    }

    @Override
    public String toString() {
        return super.toString() + " " + super.getWert() + " Euro Gage";
    }
}

import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Probe extends Termin {

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer, teilnehmer und miete sollten nicht null sein.
     */
    public Probe(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int miete) {
        super((miete > 0) ? -miete : miete, ort, datum, dauer, teilnehmer);
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebene Probe sollte nicht null sein.
     */
    public Probe(Termin t) {
        super(t);
    }
    
    @Override
    public int getKategorie() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString() + " " + super.getWert() + " Euro Miete";
    }
}
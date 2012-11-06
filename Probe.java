import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Probe extends Termin {

    //Vorbedingung: ort, datum, dauer, teilnehmer und miete != null
    public Probe(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int miete) {
        super((miete > 0) ? -miete : miete, ort, datum, dauer, teilnehmer);
    }
    
    //Vorbedingung: Probe != null
    public Probe(Termin t) {
        super(t);
    }
    
    @Override
    //Nachbedingung: return 2
    public int getKategorie() {
        return 2;
    }

    @Override
    public String toString() {
        return super.toString() + " " + super.getWert() + " Euro Miete";
    }
}

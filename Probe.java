import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Matthias
 */
public class Probe extends Termin {

    private int miete;

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer, teilnehmer und miete sollten nicht null sein.
     */
    public Probe(Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer, int miete) {
        super(ort, datum, dauer, teilnehmer);
        this.miete = (miete>0)?-miete:miete;
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebene Probe sollte nicht null sein.
     */
    public Probe(Termin t) {
        super(t);
        Probe p = (Probe) t;
        this.miete = p.getMiete();
    }
    
    /**
     * Vorbedingung
     * 
     * Uebergebene Probe sollte nicht null sein.
     * 
     * Nachbedingung
     * 
     * Retournierte Probe hat die Daten der uebergebenen Probe uebernommen.
     */
    public Probe setProbe(Probe p) {
        super.pushToStack(new Probe(this));
        super.setTermin(p);
        this.miete = p.getMiete();
        return this;
    }
    
    public int getMiete() {
        return miete;
    }

    @Override
    public String toString() {
        return super.toString() + " " + miete + " Euro Miete";
    }
}
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Matthias
 */
public abstract class Termin extends Posten {

    private Ort ort;
    private String dauer;
    private Stack<Termin> stack;
    private HashSet<Mitglied> teilnehmer;

    /**
     * Vorbedingung
     * 
     * ort, datum, dauer und teilnehmer sollten nicht null sein.
     */
    public Termin(int wert,Ort ort, GregorianCalendar datum, String dauer, HashSet<Mitglied> teilnehmer) {
        super(wert,datum);
        this.ort = ort;
        this.dauer = dauer;
        this.stack = new Stack<Termin>();
        this.teilnehmer = teilnehmer;
        
        for(Mitglied m : teilnehmer) {
            m.addTermin(this);
        }
    }

    /**
     * Vorbedingung
     * 
     * Uebergebener Termin darf nicht null sein, da sonst eine NullpointerException entsteht.
     */
    public Termin(Termin t) {
        super(t.getWert(),t.getDatum());
        this.ort = t.getOrt();
        this.dauer = t.getDauer();
        this.stack = new Stack<Termin>();
        this.teilnehmer = new HashSet<Mitglied>(t.getTeilnehmer());
    }

    /**
     * Vorbedingung
     * 
     * Uebergebener Termin darf nicht null sein, da sonst eine NullpointerException entsteht.
     * 
     * Nachbedingung
     * 
     * Retournierter Termin hat die Daten des uebergebenen Termins uebernommen.
     */
    public Termin setTermin(Termin t) {
        super.setDatum(t.getDatum());
        this.ort = t.getOrt();
        this.datum = t.getDatum();
        this.dauer = t.getDauer();
        this.teilnehmer = t.getTeilnehmer();
        return this;
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Teilnehmer hinzugefuegt werden und ihm der Termin uebergeben werden konnte
     * false andernfalls.
     */
    public boolean teilnehmerHinzufuegen(Mitglied m) {
        boolean ok = teilnehmer.add(m);
        if (ok) {
            m.addTermin(this);
        }
        return ok;
    }
    
    /**
     * Nachbedingung
     * 
     * Retournierter Wert immer
     * true, falls der Teilnehmer entfernt und ihm der Termin entzogen werden konnte,
     * false andernfalls.
     */
    public boolean teilnehmerEntfernen(Mitglied m) {
        boolean ok = teilnehmer.remove(m);
        if (ok) {
            m.terminLoeschen(this);
        }
        return ok;
    }

    public String getDauer() {
        return dauer;
    }
    
    public Ort getOrt() {
        return ort;
    }
    
    public HashSet<Mitglied> getTeilnehmer() {
        return teilnehmer;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return ort + " " + sdf.format(datum.getTime()) + " - " + dauer+"-"+getWert()+" Euro";
    }
}